package com.omg.evn.util.sysutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;


public class PropertiesUtil {
	
	private String filename;
	private Properties p;
	private FileInputStream in;
	private FileOutputStream out;

	public PropertiesUtil(String filename) {
		try {
			p = new Properties();
			//1.缓存
			//p.load(PropertiesUtil.class.getResourceAsStream("/system-config.txt"));
			//String path = PropertiesUtil.class.getResource(filename).getPath().toString();//类路径
			//this.filename = path;
			//2.相对路径
			String path = PropertiesUtil.class.getResource(filename).getPath().toString();//类路径
			this.filename = path;
			in = new FileInputStream(new File(path));
			p.load(in);
			//3.完整路径
			//this.filename = filename;
			//in = new FileInputStream(new File(path));
			//p.load(in);
			
			if (in != null) in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("配置文件"+filename+"找不到！");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("读取配置文件"+filename+"错误！");
			e.printStackTrace();
		}
	}

	public static String getConfigFile(HttpServlet hs) {
		return getConfigFile(hs, "config.properties");
	}

	private static String getConfigFile(HttpServlet hs, String configFileName) {
		String configFile = "";
		ServletContext sc = hs.getServletContext();
		configFile = sc.getRealPath("/" + configFileName);
		if (configFile == null || configFile.equals("")) {
			configFile = "/" + configFileName;
		}
		return configFile;
	}

	public void list() {
		p.list(System.out);
	}

	public String getValue(String itemName) {
		return p.getProperty(itemName);
	}

	public String getValue(String itemName, String defaultValue) {
		return p.getProperty(itemName, defaultValue);
	}

	//UTF-8编码
	public String getValueCN(String itemName) {
		try {
			return new String(p.getProperty(itemName).getBytes("Latin1"), "utf-8");//读取utf-8编码文件
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	//GBK编码
	public String getValueGBK(String itemName) {
		try {
			return new String(p.getProperty(itemName).getBytes("Latin1"), "GBK");//读取GBK编码文件
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public void setValue(String itemName, String value) {
		p.setProperty(itemName, value);
	}

	public void saveFile(String filename, String description) throws Exception {
		try {
			File f = new File(filename);
			out = new FileOutputStream(f);
			p.store(out, description);
			out.close();
		} catch (IOException ex) {
			throw new Exception("无法保存指定的配置文件:" + filename);
		}
	}

	public void saveFile(String filename) throws Exception {
		saveFile(filename, "");
	}

	public void saveFile() throws Exception {
		if (filename.length() == 0) 
			throw new Exception("需指定保存的配置文件名");
		saveFile(filename);
	}

	public void deleteValue(String value) {
		p.remove(value);
	}

	public static void main(String args[]) {
		String basePath = "";
		String filePath = "";
		basePath = ClassLoader.getSystemClassLoader().getResource("").getPath().replaceAll("%20"," ");//获取类路径
		System.out.println("类路径："+basePath);
		basePath = basePath.substring(0, basePath.lastIndexOf("WebRoot"));
		filePath = basePath + "src/demo.properties";//模板路径
		System.out.println("模板路径："+filePath);
		
		PropertiesUtil pu = new PropertiesUtil(filePath);
		pu.list();
		
		pu.setValue("name", "wang");
		try {
			pu.saveFile(filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
