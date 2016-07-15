package com.omg.evn.util.fileutil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;

/**
 * 文件上传工具
 * @Title: FileUploadUtil.java
 * @Description: TODO()
 * @author: zyen
 * @date: 2014-9-16 下午6:27:18
 * @最后修改人: wt
 * @最后修改时间: 2014-9-16 下午6:27:18
 * @version V1.0
 * @copyright:  
 */
public class FileUploadUtil {

	/**
	 * 获取webapps路径
	 * @Description: TODO() 
	 * @return
	 */
	public static String getWebappsPath(){
		String path = FileUploadUtil.class.getResource("/").getPath().toString();//类路径
        path = path.replaceAll("%20", " ");//URLDecoder.decode(path,"utf-8");//空格(%20) 处理
        String saveFilePath = new File(path).getParentFile().getParentFile().getParentFile().getAbsolutePath();
        //System.out.println(saveFilePath);
        //String saveFilePath = path.substring(0, path.indexOf("webapps"))+"webapps";
		return saveFilePath;
	}
	
	/**
	 * 文件上传路径 临时目录(相对路径)
	 * 临时目录 定时清理()
	 * eg: /upload/temp/20140808/
	 * @Description: TODO() 
	 * @return
	 */
	public static String getSaveFileTempPath(){
        String saveFilePath = getWebappsPath();//获取webapps路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String yyyyMMddStr = sdf.format(new Date());//获取yyyyMM格式时间
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
		c.set(Calendar.DATE, c.get(Calendar.DATE)-1);//日期前一天前一天
		String yesterday = sdf.format(c.getTime());  //获取昨天日期
        saveFilePath += "/upload/temp/";//临时目录
        String [] exceptPaths = new String[]{yyyyMMddStr,yesterday};
        //删除临时文件
        FileUploadUtil.deleteTempFile(saveFilePath,exceptPaths);//删除临时文件 排除yyyyMMStr文件夹
        saveFilePath += yyyyMMddStr+"/";//存放目录
        //创建目录
        FileUtil.createDir(saveFilePath);//创建目录
        //System.out.println("test");
        String saveFileTempPath = "/upload/temp/"+yyyyMMddStr+"/";
		return saveFileTempPath;
	}
	
	/**
	 * 文件上传路径 正式目录(相对路径)
	 * eg: /upload/appendix/201408/20140808/
	 * @Description: TODO() 
	 * @return
	 */
	public static String getSaveFileRealPath(){
		SimpleDateFormat yyyysdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat yyyyMMsdf = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat yyyyMMddsdf = new SimpleDateFormat("yyyyMMdd");
        Date nowDate = new Date();
        String yyyyStr   = yyyysdf.format(nowDate);  //获取yyyy格式时间
        String yyyyMMStr   = yyyyMMsdf.format(nowDate);  //获取yyyyMM格式时间
        String yyyyMMddStr = yyyyMMddsdf.format(nowDate);//获取yyyyMMdd格式时间
        String saveBaseUrl = "/upload/appendix/"+yyyyStr+"/"+yyyyMMStr+"/"+yyyyMMddStr+"/";//正式目录;
		return saveBaseUrl;
	}
	
	/**
	 * 拷贝文件至正式路径
	 * @Description: TODO() 
	 * @param appendix
	 */
	public static String copyFile(String filePath){
		//构建保存路径
        String fileBasePath = getWebappsPath();//获取webapps路径
        String saveBaseUrl = getSaveFileRealPath();//文件上传路径 正式目录(相对路径)
        String saveFilePath = fileBasePath + getSaveFileRealPath();//文件上传路径 正式目录
        //创建目录
        FileUtil.createDir(saveFilePath);//创建目录
        
		String copyFilePath = filePath;
		//附件路径处理 存在临时文件才复制
		if (filePath != null && !"".equals(filePath) && filePath.indexOf("temp")>-1) {
			File file = new File(fileBasePath + filePath);
			if (file.exists()) {
				try {
					FileUtils.copyFileToDirectory(file, new File(saveFilePath));
					copyFilePath = saveBaseUrl + filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return copyFilePath;
	}

	/**
	 * 删除临时文件
	 * @Description: TODO() 
	 * @param tempFilePath 临时文件夹
	 * @param yyyyMMStr		排除文件夹
	 */
	public static boolean deleteTempFile(String tempFilePath,String[] exceptPaths){
		boolean flag = false;
		//两天删除一次
		File dirFile = new File(tempFilePath);
		//如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		//删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		//如果只存在2个文件夹，不处理
		if(files.length > 2){
			for (int i = 0; i < files.length; i++) {
				//System.out.println("存在临时文件");
				//排除文件夹
				if (exceptPaths!=null && exceptPaths.length>1) {
					boolean isExists = false;
					for (int j = 0; j < exceptPaths.length; j++) {
						if (files[i].getPath().endsWith(exceptPaths[j])) {
							isExists = true;
						}
					}
					if (isExists) {
						//System.out.println("排除当前："+files[i].getPath());
						continue;
					}
				}
				//删除子文件
				if (files[i].isFile()) {   
					flag = FileUtil.deleteFile(files[i].getAbsolutePath());   
					if (!flag) break;   
				//删除子目录   
				}else {   
					flag = FileUtil.deleteDirectory(files[i].getAbsolutePath());   
					if (!flag) break;   
				}
			}
		}else {
			//System.out.println("不存在临时文件");
		}
		return flag;
	}
	
}
