<%
/* *
 *功能：境外收单-ETC单笔退款接入页
 *版本：3.3
 *日期：2012-08-14
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *************************注意*****************
 *如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
 *1、商户服务中心（https://b.alipay.com/support/helperApply.htm?action=consultationApply），提交申请集成协助，我们会有专业的技术工程师主动联系您协助解决
 *2、商户帮助中心（http://help.alipay.com/support/232511-16307/0-16307.htm?sh=Y&info_type=9）
 *3、支付宝论坛（http://club.alipay.com/read-htm-tid-8681712.html）
 *如果不想使用扩展功能请把扩展功能参数赋空值。
 **********************************************
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝境外收单-ETC单笔退款</title>
	</head>
	<%
		////////////////////////////////////请求参数//////////////////////////////////////

		//Refund no
		String out_return_no = new String(request.getParameter("WIDout_return_no").getBytes("ISO-8859-1"),"UTF-8");
		//required

		//Old Partner transaction ID
		String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//required

		//Refund sum
		String return_amount = new String(request.getParameter("WIDreturn_amount").getBytes("ISO-8859-1"),"UTF-8");
		//required

		//Currency
		String currency = "required";
		//Refer to abbreviation of currencies
		//Refund Transaction time
		String gmt_return = "required";
		//YYYYMMDDHHMMSS Beijing Time
		//Reason for refundament
		String reason = new String(request.getParameter("WIDreason").getBytes("ISO-8859-1"),"UTF-8");
		//required
		
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "forex_refund");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("out_return_no", out_return_no);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("return_amount", return_amount);
		sParaTemp.put("currency", currency);
		sParaTemp.put("gmt_return", gmt_return);
		sParaTemp.put("reason", reason);
		//단일 환불 인터페이스를 통해 파트너는 실시간으로 한 거래를 환불 할 수 있습니다.
		//1.환불 합계의 합계가 원래 지불 금액을 초과 할 수 없습니다.
		//2. 환불은 지불 후 일정 기간 내에 이루어져야합니다 (계약서에 명시되어 있으며 기본값은 3 개월입니다).
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
	%>
	
	<font clorl=><strong><font color="#800040">REQUEST</font></strong><br><%=sParaTemp %><br>
	<font clorl=><strong><font color="#800040">RESULT</font></strong><br><%=sHtmlText %><br>
	<body>
	</body>
</html>
