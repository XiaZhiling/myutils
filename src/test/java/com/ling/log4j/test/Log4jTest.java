package com.ling.log4j.test;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jTest {
	
	private static Logger log = Logger.getLogger(Log4jTest.class.getName());
	
	@Test
	public void testLog(){
		log.debug("This is a debug Message!");
		
		log.info("This is an info Message!");
		
		log.error("This is an error Message!");
	}

}
