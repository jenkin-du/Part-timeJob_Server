package com.stitp.util;

import java.io.UnsupportedEncodingException;

public class Encoder {

	public static String EncodeToUTF_8(String src){
		
		String res=null;
		try {
			byte[] srcBytes=src.getBytes("gbk");
			res=new String(srcBytes,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
