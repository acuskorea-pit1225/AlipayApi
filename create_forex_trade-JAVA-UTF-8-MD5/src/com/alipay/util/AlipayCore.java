package com.alipay.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.PartSource;

import com.alipay.config.AlipayConfig;

/* *
 *类名：AlipayFunction
 *功能：支付宝接口公用函数类
 *详细：该类是请求、通知返回两个文件所调用的公用函数核心处理文件，不需要修改
 *版本：3.3
 *日期：2012-08-14
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 * 클래스 이름 : AlipayFunction
 * 기능 : Alipay 인터페이스 공통 기능 클래스
 * 세부 정보 : 클래스가 요청되면 알림은 공용 함수 코어 처리 파일에 의해 호출되는 두 개의 파일을 반환하며 수정할 필요가 없습니다.
 * 버전 : 3.3
 * 날짜 : 2012-08-14
 * 설명 :
 * 다음 코드는 샘플 코드에서 제공하는 비즈니스 테스트의 편의를 위해서만 제공되며 기업은 자체 기술 요구 사항에 따라 기술 문서에 따라 코드를 반드시 사용해야하는 것은 아닙니다.
 * 코드는 학습 및 연구 Alipay 인터페이스 사용을위한 것입니다, 그냥 참조를 제공합니다.
 */

public class AlipayCore {

    /** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     * 배열에서 null 및 signature 매개 변수를 제거합니다.
     * @param sArray 서명 파라미터 그룹
     * @return는, 서명 파라미터를 사용해 null 값의 뒤에 새로운 서명 그룹을 삭제합니다
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     * 배열의 모든 요소를 ​​정렬하고 "매개 변수 = 매개 변수 값"모드에 따라 "&"문자를 문자열에 정렬합니다.
     * @param params는 매개 변수 그룹의 문자 스티칭을 정렬하고 참여해야합니다.
     * @return 연결된 문자열
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符 스티치에는 마지막 문자 및 문자가 포함되지 않습니다.
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     * 로그를 작성하고 테스트하기 쉽습니다. (사이트 요구 사항을 참조하십시오. 데이터베이스로 레코드를 변경할 수도 있습니다)
     * @param 로그에 텍스트의 내용을 쓰는 sWord
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(AlipayConfig.log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /** 
     * 生成文件摘要
     * @param strFilePath 文件路径
     * @param file_digest_type 摘要算法
     * @return 文件摘要结果
     * 파일 요약 생성
     * @param strFilePath 파일 경로
     * @param file_digest_type 요약 알고리즘
     * @return 파일 요약 결과
     */
    public static String getAbstract(String strFilePath, String file_digest_type) throws IOException {
        PartSource file = new FilePartSource(new File(strFilePath));
    	if(file_digest_type.equals("MD5")){
    		return DigestUtils.md5Hex(file.createInputStream());
    	}
    	else if(file_digest_type.equals("SHA")) {
    		return DigestUtils.sha256Hex(file.createInputStream());
    	}
    	else {
    		return "";
    	}
    }
}
