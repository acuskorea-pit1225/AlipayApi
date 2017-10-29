<%
/* *
 *功能：境外收单交易接口接入页
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *************************注意*****************
 *如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
 *1、开发文档中心（https://ds.alipay.com/fd-ij9mtflt/home.html）
 *2、支持中心（https://global.alipay.com/service/service.htm）
 *3、支持邮箱（overseas_support@service.alibaba.com）
 *如果不想使用扩展功能请把扩展功能参数赋空值。
 **********************************************

* 기능 : 해외 영수증 거래 인터페이스 접속 페이지
 * 버전 : 3.4
 * 수정 날짜 : 2016-03-08
 * 설명 :
 * 다음 코드는 샘플 코드에서 제공하는 비즈니스 테스트의 편의를 위해서만 제공되며 기업은 자체 기술 요구 사항에 따라 기술 문서에 따라 코드를 반드시 사용해야하는 것은 아닙니다.
 * 코드는 학습 및 연구 Alipay 인터페이스 사용을위한 것입니다, 그냥 참조를 제공합니다.

 *************************주의 *****************
 * 인터페이스 통합 프로세스에서 문제가 발생하는 경우 다음 경로를 따라 해결할 수 있습니다.
 * 1, 개발 문서 센터 (https://ds.alipay.com/fd-ij9mtflt/home.html)
 * 2, 지원 센터 (https://global.alipay.com/service/service.htm)
 * 3, 지원 이메일 (overseas_support@service.alibaba.com)
 * 확장 기능을 사용하지 않으려면 기능 매개 변수를 확장하십시오.
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝境外收单交易接口</title>
	</head>
	<%
		////////////////////////////////////请求参数//////////////////////////////////////
		//첫 메인페이지의 정보를 받아온다. 
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //订单名称，必填
        String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");

        //付款金额，必填
        String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("ISO-8859-1"),"UTF-8");

        //商品描述，可空
        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
        
        //币种，不可空
        String currency = new String(request.getParameter("WIDcurrency").getBytes("ISO-8859-1"),"UTF-8");
		
		//////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		//把请求参数打包成数组
		//요청 매개 변수를 배열로 암호화
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service); //서비스 종류
        sParaTemp.put("partner", AlipayConfig.partner); //파트너사 정보
        sParaTemp.put("_input_charset", AlipayConfig.input_charset); 
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("currency", currency);
		//其他业务参数根据在线开发文档，添加参数
		//如sParaTemp.put("参数名","参数值");
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		out.println(sHtmlText);
	%>
	<body>
	</body>
</html>
