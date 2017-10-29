package com.alipay.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.alipay.config.AlipayConfig;
import com.alipay.sign.MD5;

/* *
 *类名：AlipayNotify
 *功能：支付宝通知处理类
 *详细：处理支付宝各接口通知返回
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考

 *************************注意*************************
 *调试通知返回时，可查看或改写log日志的写入TXT里的数据，来检查通知返回是否正常
 ** 수업 명 : AlipayNotify
 * 기능 : Alipay 알림 처리 클래스
 * 세부 정보 : 처리 Alipay 인터페이스 알림 반환
 * 버전 : 3.3
 * 날짜 : 2012-08-17
 * 설명 :
 * 다음 코드는 샘플 코드에서 제공하는 비즈니스 테스트의 편의를 위해서만 제공되며 기업은 자체 기술 요구 사항에 따라 기술 문서에 따라 코드를 반드시 사용해야하는 것은 아닙니다.
 * 코드는 학습 및 연구 Alipay 인터페이스를 사용하기위한 것입니다, 그냥 참조를 제공하기 위해

 ************************주의 ************************* *
 * 디버그 알림이 반환되면 TXT의 로그 데이터를 보거나 덮어 써 알림이 정상인지 여부를 확인할 수 있습니다
 */
public class AlipayNotify {

    /**
     * 支付宝消息验证地址
     */
    private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

    /**
     * 验证消息是否是支付宝发出的合法消息
     * @param params 通知返回来的参数数组
     * @return 验证结果
		메시지가 Alipay의 합법적 인 메시지인지 확인하십시오.
     	파라미터 : params - 반환되는 인수의 배열을 통지합니다.
     * @return 결과를 검증한다
     * 
     */
    public static boolean verify(Map<String, String> params) {

        //判断responsetTxt是否为true，isSign是否为true
        //responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
        //isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
    	//responsetTxt가 true인지, isSign이 true인지 확인합니다.
        // responsetTxt 결과가 true가 아니며 서버 설정 문제, 파트너 ID, notify_id 1 분 실패와 관련 있음
        // isSign은 보안 검사 코드와 함께 true가 아니며 매개 변수 형식 (예 : 사용자 지정 매개 변수 등), 코딩 형식
    	String responseTxt = "false";
		if(params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id);
		}
	    String sign = "";
	    if(params.get("sign") != null) {sign = params.get("sign");}
	    boolean isSign = getSignVeryfy(params, sign);

        //写日志记录（若要调试，请取消下面两行注释）
        //String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign + "\n 返回回来的参数：" + AlipayCore.createLinkString(params);
	    //AlipayCore.logResult(sWord);
	    
        if (isSign && responseTxt.equals("true")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean verifyReturn(Map<String, String> params) {
	    String sign = "";
	    if(params.get("sign") != null) {sign = params.get("sign");}
	    boolean isSign = getSignVeryfy(params, sign);

        //写日志记录（若要调试，请取消下面两行注释）
        //String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign + "\n 返回回来的参数：" + AlipayCore.createLinkString(params);
	    //AlipayCore.logResult(sWord);

        return isSign;
    }

    /**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @return 生成的签名结果
     */
	private static boolean getSignVeryfy(Map<String, String> Params, String sign) {
    	//过滤空值、sign与sign_type参数
    	Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
        //获取待签名字符串
        String preSignStr = AlipayCore.createLinkString(sParaNew);
        //获得签名验证结果
        boolean isSign = false;
        if(AlipayConfig.sign_type.equals("MD5") ) {
        	isSign = MD5.verify(preSignStr, sign, AlipayConfig.key, AlipayConfig.input_charset);
        }
        return isSign;
    }

    /**
    * 获取远程服务器ATN结果,验证返回URL
    * @param notify_id 通知校验ID
    * @return 服务器ATN结果
    * 验证结果集：
    * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
    * true 返回正确信息
    * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
    * 원격 서버 ATN 결과 가져 오기, 반환 URL 확인
    * @param notify_id가 유효 ID를 통지합니다.
    * @return 서버 ATN 결과
    * 결과 집합 확인 :
    * 잘못된 명령 매개 변수가이 오류로 표시되지 않습니다. 반환 프로세스 파트너를 확인하십시오. 키가 비어 있습니다.
    * true는 올바른 정보를 반환합니다.
    * false 포트 문제를 방지하고 시간이 1 분 이상인지 확인하려면 방화벽이나 서버를 확인하십시오.
    */
    private static String verifyResponse(String notify_id) {
        //获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求

        String partner = AlipayConfig.partner;
        String veryfy_url = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;

        return checkUrl(veryfy_url);
    }

    /**
    * 获取远程服务器ATN结果
    * @param urlvalue 指定URL路径地址
    * @return 服务器ATN结果
    * 验证结果集：
    * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
    * true 返回正确信息
    * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
    * 원격 서버 ATN 결과 얻기
    * @param urlvalue URL 경로 주소를 지정합니다.
    * @return 서버 ATN 결과
    * 결과 집합 확인 :
    * 잘못된 명령 매개 변수가이 오류로 표시되지 않습니다. 반환 프로세스 파트너를 확인하십시오. 키가 비어 있습니다.
    * true는 올바른 정보를 반환합니다.
    * false 포트 문제를 방지하고 시간이 1 분 이상인지 확인하려면 방화벽이나 서버를 확인하십시오.
    */
    private static String checkUrl(String urlvalue) {
        String inputLine = "";

        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection
                .getInputStream()));
            inputLine = in.readLine().toString();
        } catch (Exception e) {
            e.printStackTrace();
            inputLine = "";
        }

        return inputLine;
    }
}
