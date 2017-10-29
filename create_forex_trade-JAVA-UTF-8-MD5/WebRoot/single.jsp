<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="gbk"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Ö§ï¿½ï¿½ï¿½ï¿½Ö§ï¿½ï¿½</title>
</head>
<%
String paygateway	=	"https://openapi.alipaydev.com/gateway.do?";	//'Ö§?½ï¿½?½Ó¿ï¿½
String service      = "single_trade_query";//?½ï¿½?½Ù¸ï¿½?½î½»???¿½?½ï¿½
String sign_type       = "MD5";
String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
String input_charset   =  "gbk";       
String partner			=	"2088101181812821"; 
String key             =    "p6aisa8pp9syaoy0i554o2g4qum12qvn"; 

	String ItemUrl=Payment.CreateUrl(paygateway,service,sign_type,out_trade_no,input_charset,partner,key);
	dom4j dom4=new dom4j();
	String result=dom4.DomXml(ItemUrl);
%>

<font clorl=><strong><font color="#800040">REQUEST</font></strong><br><%=ItemUrl %><br>
<font clorl=><strong><font color="#800040">RESPONSE</font></strong><br><%=result %><br>

