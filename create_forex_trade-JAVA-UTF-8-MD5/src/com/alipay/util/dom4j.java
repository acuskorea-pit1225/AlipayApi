package com.alipay.util;

import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class dom4j {
	String filepath = "";

	public String DomXml(String filepath) throws Exception {
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new URL(filepath).openStream());

		List<Node> nodeList = doc.selectNodes("/alipay/response/trade/*");
		StringBuffer buf = new StringBuffer();
		for (Node node : nodeList) {
			buf.append(node.getName()).append("=").append(node.getText())
					.append("&");
		}
		 System.out.println(buf.toString());
		return buf.toString();
	}

	public static void main(String[] args) throws Exception {
		/*dom4j dom = new dom4j();
		String sss = dom.dd("https://www.alipay.com/cooperate/gateway.do?service=single_trade_query&partner=2088002029290264&_input_charset=utf-8&out_trade_no=237&sign=abaab98dbff75a3712ec7a69e295a80b&sign_type=MD5");
		System.out.println(sss);*/

	}

}
