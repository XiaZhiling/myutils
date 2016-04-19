package com.ling.utils;

import org.junit.Test;

public class EncryptUtilTest {
	
	@Test
	public void testGetSHA1(){
		System.out.println(EncryptUtil.getSha1("xiazhil"));
	}
	
	@Test
	public void testGetMD5(){
		//8255d6fa0bdd98ad2f25f4b32a567496
		System.out.println(EncryptUtil.getMD5("xiazhil", "UTF-8"));
	}

}
