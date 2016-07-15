package com.omg.evn.util.fileutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ReadProperties{

	public static String read(String path){
		File pFile = new File(path); 
		FileInputStream pInStream = null;
		try {
			pInStream = new FileInputStream(pFile);

		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
		Properties p = new Properties();
		try {
			p.load(pInStream);
		} catch (IOException e){
			e.printStackTrace(); 
		}
		String url = p.getProperty("jmx.rmi_port");
		System.out.print("配置文件地址= " + url);
		return url;
	}

	public static String getPoperitesValueByKey(String key){
		Properties p=new Properties();
		InputStream is= new ReadProperties().getClass().getResourceAsStream("/application.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value  =p.getProperty(key);
		return value;
	}
	public static String getSystemValue(String key){
		Properties p=new Properties();
//		InputStream is= new ReadProperties().getClass().getResourceAsStream("/sysControl.properties");
		InputStream is= new ReadProperties().getClass().getResourceAsStream("/application.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value  =p.getProperty(key);
		return value;
	}
	
	
	public static String getSystemValueNew(String key){
//		ResourceBundle systool = ResourceBundle.getBundle("sysControl");//application.properties配置文件的名称
		ResourceBundle systool = ResourceBundle.getBundle("application");//application.properties配置文件的名称
		return systool.getString(key);
	}
	
}
