package com.silvassa.util;

import java.util.ResourceBundle;



public class MmiPathController {
	

	private static ResourceBundle rb;
	/**
	 * Static block to initialize the resource bundle
	 */
	static {
//		rb = ResourceBundle.getBundle("jdbc");
		rb = ResourceBundle.getBundle("com/silvassa/resource/ApplicationResources");
	}

	public static String getDataPath(String key) {
		return rb.getString(key);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Entering for validating user"+getDataPath("notice.cmd"));

	}
	
}
