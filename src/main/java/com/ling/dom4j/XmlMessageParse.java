package com.ling.dom4j;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlMessageParse {
	
	public static void parse(InputStream io){
		
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(io);
			Element rootElement = document.getRootElement();
			
//			@SuppressWarnings("unchecked")
//			List<Element> elements = rootElement.elements();
//			if(elements !=null){
//				System.out.println("Not null!");
//				System.out.println(elements.size());
//			}
			
			Element e =rootElement.element("book");
			if(e == null){
				System.out.println("sdfs");
			}else{
				System.out.println(e.getName());
			}
			
//			System.out.println(e.asXML());
			
			Document document2 = DocumentHelper.createDocument();
			document2.setXMLEncoding("GBK");
			System.out.println(document2.asXML());
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
