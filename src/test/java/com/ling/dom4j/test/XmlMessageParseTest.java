package com.ling.dom4j.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;

import com.ling.dom4j.XmlMessageParse;

public class XmlMessageParseTest {
	
	@Test
	public void testParse(){
		
		InputStream io;
		try {
			io = new FileInputStream(new File("src/test/java/com/ling/dom4j/test/xzm.xml"));
			XmlMessageParse.parse(io);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
