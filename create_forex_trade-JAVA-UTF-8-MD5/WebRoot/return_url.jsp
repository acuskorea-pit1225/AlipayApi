<%
/* *
 功能：支付宝页面跳转同步通知页面
 版本：3.2
 日期：2011-03-17
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 该页面可在本机电脑测试
 可放入HTML等美化页面的代码、商户业务逻辑程序代码
 TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
 TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
 //********************************
 페이지는 로컬 컴퓨터에서 테스트 할 수 있습니다.
 HTML 및 기타 조경 페이지 코드, 비즈니스 비즈니스 로직 프로그램 코드에 넣을 수 있습니다
 TRADE_FINISHED (트랜잭션이 성공적으로 끝났으며 트랜잭션에서 더 이상 수행 할 수 없음을 나타냄).
 TRADE_SUCCESS (해당 트랜잭션이 성공적으로 완료되었으므로 다음과 같이 트랜잭션에 대한 후속 작업을 수행 할 수 있습니다 : 서브 실행, 환불 등);
 * */
%>
<style>
.button {
    display: block;
    width: 115px;
    height: 25px;
    background: #4E9CAF;
    padding: 10px;
    text-align: center;
    border-radius: 5px;
    color: white;
    font-weight: bold;
}
</style>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="com.alipay.config.*"%>
<html>
  <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝页面跳转同步通知页面</title>
  </head>
  <body>
<%
	//获取支付宝GET过来反馈信息
	Map<String,String> params = new HashMap<String,String>();
	Map requestParams = request.getParameterMap();
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
		params.put(name, valueStr);
	}
	
	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
	//商户订单号
	String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

	//支付宝交易号

	String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

	//交易状态
	String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
	
	
	
	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
	
	//计算得出通知验证结果
	boolean verify_result = AlipayNotify.verifyReturn(params);
	
	if(verify_result){//验证成功
		//////////////////////////////////////////////////////////////////////////////////////////
		//请在这里加上商户的业务逻辑程序代码

		//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
		if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
			//判断该笔订单是否在商户网站中已经做过处理
			//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
			//如果有做过处理，不执行商户的业务程序
				
			//주문이 판매자 웹 사이트에서 처리되었는지 여부 확인
			// 주문 처리를 완료하지 않은 경우 주문 번호 (out_trade_no)에 따라 판매자 웹 사이트 주문 시스템에서 주문 세부 정보를 찾고 비즈니스 비즈니스 절차를 구현합니다
			// 처리를 완료 한 경우 비즈니스 프로세스 비즈니스를 구현하지 마십시오.
		}
		
		//该页面可做页面美工编辑
		out.println("success<br/>");
		//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
		//////////////////////////////////////////////////////////////////////////////////////////
	}else{
		//该页面可做页面美工编辑
		out.println("验证失败");
	}
%>

<form action="single.jsp" class="alipayform" method="POST" target="_blank">
		<table border="1">
			<tr>
				<td>파트너 거래 정보 : <%=out_trade_no%></td>
			</tr>
			<tr>
				<td>알리페이 거래 정보 : <%=trade_no%></td>
			</tr>
			<tr>
				<td>거래 상태 : <%=trade_status%></td>
			</tr>
		</table>
	 <input type="button" value="상세 조회" onclick="location.href='single.jsp?out_trade_no=<%=out_trade_no%>';"/>
	<input type="button" value="환불" onclick="location.href='refund.jsp?out_trade_no=<%=out_trade_no%>';"/> 
	</form>
	

	
</body>
</html>