package com.stitp.util;
import java.io.UnsupportedEncodingException;   
public class Encode {
	public static String doEncoding(String string)
	 {
	     try {
	  string= new String(string.getBytes("ISO-8859-1"), "UTF-8");
	  } catch (UnsupportedEncodingException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  } 
	  return string;
	 }
}
