package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 * 종류 : AlipayConfig
 * 기능 : 기반 구조 배열
 * 세부 정보 :
 * 版本 : 3.4
 * 수정 일 : 2016-03-08
 * 설명 :
 * 고객의 요구 사항을 충족시키지 못하는 경우, 귀하는 고객의 요구에 따라 서비스를 제공 할 수 있습니다.
 * 학부모와 연구 보조 담당자 사용, 한 사람에게 제공.
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	
	// 파트너 ID, 서명 된 계정, 2088로 시작 16 자리 시리얼 번호, 주소 참조 : https : //b.alipay.com/order/pidAndKey.htm
	//UID정보 
	public static String partner = "2088101181812821";
	
	// MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm

    //MD5 키, 보안 검사 코드, 숫자와 문자로 구성된 32 비트 문자열 (https : //b.alipay.com/order/pidAndKey.htm)
    public static String key = "p6aisa8pp9syaoy0i554o2g4qum12qvn";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问

    //서버 비동기 알림 페이지 경로 http : / / 형식 전체 경로, ID를 추가 할 수 없습니다 = 123 이러한 사용자 지정 매개 변수, 외부 네트워크는 정상적인 액세스해야합니다
    public static String notify_url = "http://localhost:8080/create_forex_trade-JAVA-UTF-8-MD5/notify_url.jsp";
    
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//페이지 이동 동기화 통지 페이지 경로는 http : // 형식 전체 경로이며 id = 123과 같은 사용자 정의 매개 변수를 추가 할 수 없으며 외부 네트워크는 정상 액세스 여야합니다
    public static String return_url = "http://localhost:8080/create_forex_trade-JAVA-UTF-8-MD5/return_url.jsp";

	// 签名方式
    //암호화 타입
    public static String sign_type = "MD5";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	//디버깅하려면 TXT 로그 폴더 경로를 만들고 AlipayCore.java 클래스의 logResult (String sWord) 인쇄 메소드를 참조하십시오.
    //알리페이 코어 로그 경로 지정
    public static String log_path = "D:\\";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
    //문자 인코딩 형식은 현재 gbk 또는 utf-8을 지원합니다.
	//캐릭터셋 설정
    public static String input_charset = "utf-8";
		

	// 调用的接口名，无需修改
    //인터페이스 이름을 호출하고 수정할 필요가 없습니다.
	public static String service = "create_forex_trade";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
}

