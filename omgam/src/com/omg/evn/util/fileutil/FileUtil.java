package com.omg.evn.util.fileutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

public class FileUtil {
	
	/**
	 * 保存文件
	 * @param file 上传的文件
	 * @param fileSavePath 保存路径
	 * @throws IOException
	 */
	public static void saveFile(File file,String fileSavePath) throws IOException{
		InputStream is = new FileInputStream(file); //根据上传文件得到输入流
		OutputStream os = new FileOutputStream(fileSavePath); //指定输出流地址
		byte buffer[] = new byte[2048];
		int count = 0;
		int filesize = 0;
		while ((count = is.read(buffer)) > 0) {
			filesize+=buffer.length;
			os.write(buffer, 0, count); //把文件写到指定位置的文件中
		}
		is.close(); //关闭
		os.close();
	}
	
	/**
	* 创建目录
	* @param destDirName 目标目录名
	* @return 目录创建成功返回true，否则返回false
	*/
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if(dir.exists()) {
			//System.out.println("创建目录" + destDirName + "失败，目标目录已存在！");
			return false;
		}
		if(!destDirName.endsWith(File.separator)){
			destDirName = destDirName + File.separator;
		}
		// 创建单个目录
		if(dir.mkdirs()) {
			//System.out.println("创建目录" + destDirName + "成功！");
			return true;
		} else {
			//System.out.println("创建目录" + destDirName + "成功！");
			return false;
		}
	}
    
	/**
	 * 获取文件扩展名
	 * @param filename 带扩展名的文件名
	 * @return
	 */
	public static String getExtensionName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length() - 1))) {   
                return filename.substring(dot + 1);   
            }   
        }   
        return filename;   
    }
	
	/**
	 * 获取不带扩展名的文件名 
	 * @param filename 带扩展名的文件名
	 * @return
	 */
	public static String getFileNameNoExt(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length()))) {   
                return filename.substring(0, dot);   
            }   
        }
        return filename;   
    }
	
	/**
	 * 解压zip格式压缩包 对应的是ant.jar    *     
	 * @param sourceZip
	 * @param destDir
	 * @throws Exception
	 */
	private static void unzip(String sourceZip,String destDir) throws Exception{     
       try{     
           Project p = new Project();     
           Expand e = new Expand();     
           e.setProject(p);     
           e.setSrc(new File(sourceZip));     
           e.setOverwrite(false);     
           e.setDest(new File(destDir));     
           /*
			ant下的zip工具默认压缩编码为UTF-8编码，   
           	而winRAR软件压缩是用的windows默认的GBK或者GB2312编码   
			所以解压缩时要制定编码格式 
           */    
           e.setEncoding("gbk");     
           e.execute();     
       }catch(Exception e){     
           throw e;     
       }     
	}     
   /**
    * 解压rar格式压缩包。   
    * 对应的是java-unrar-0.3.jar，但是java-unrar-0.3.jar又会用到commons-logging-1.1.1.jar
    * @param sourceRar
    * @param destDir
    * @throws Exception
    */ 
   private static void unrar(String sourceRar,String destDir) throws Exception{     
       Archive a = null;     
       FileOutputStream fos = null;     
       try{     
           a = new Archive(new File(sourceRar));
           FileHeader fh = a.nextFileHeader();     
           List<FileHeader> list = a.getFileHeaders();
           System.out.println("list.size()="+list.size());
           for (int i = 0; i < list.size(); i++) {
        	   System.out.println("解压第"+i+"条记录时。");
        	   fh=list.get(i);
               if(!fh.isDirectory()){
                   //1 根据不同的操作系统拿到相应的 destDirName 和 destFileName
                   String compressFileName = fh.getFileNameString().trim();     
                   String destFileName = "";
                   String destDirName = "";
                   //非windows系统     
                   if(File.separator.equals("/")){     
                       destFileName = destDir + compressFileName.replaceAll("\\\\", "/");     
                       destDirName = destFileName.substring(0, destFileName.lastIndexOf("/"));     
                   //windows系统      
                   }else{     
                       destFileName = destDir + compressFileName.replaceAll("/", "\\\\");     
                       destDirName = destFileName.substring(0, destFileName.lastIndexOf("\\"));     
                   }
                   //2创建文件夹 
                   File dir = new File(destDirName);     
                   if(!dir.exists()||!dir.isDirectory()){     
                       dir.mkdirs();     
                   }     
                   //3解压缩文件 
                   fos = new FileOutputStream(new File(destFileName));
                   System.out.println("解压:"+destFileName);
                   a.extractFile(fh, fos);
                   fos.close();
                   fos = null;
               }
           }
           System.out.println("解压完毕！");
           a.close();
           a = null;
       }catch(Exception e){
           e.printStackTrace();
       }finally{     
           if(fos!=null){     
               try{fos.close();fos=null;}catch(Exception e){e.printStackTrace();}     
           }     
           if(a!=null){     
               try{a.close();a=null;}catch(Exception e){e.printStackTrace();}
           }
       }
   }
	   
   /**
    * 根据类型进行解压缩     
    * @param sourceFile
    * @param destDir
    * @throws Exception
    */
   public static void deCompress(String sourceFile,String destDir) throws Exception{     
       //保证文件夹路径最后是"/"或者"\" 
       char lastChar = destDir.charAt(destDir.length()-1);     
       if(lastChar!='/'&&lastChar!='\\'){     
           destDir += File.separator;
       }
       //根据类型，进行相应的解压缩     
       String type = sourceFile.substring(sourceFile.lastIndexOf(".")+1);     
       if(type.equals("zip")){     
           FileUtil.unzip(sourceFile, destDir);     
       }else if(type.equals("rar")){     
    	   FileUtil.unrar(sourceFile, destDir);     
       }else{     
            throw new Exception("只支持zip和rar格式的压缩包！");     
       }     
   }
   
   /**  删除文件     **/
   
   /**  
    *  根据路径删除指定的目录或文件，无论存在与否  
    *@param sPath  要删除的目录或文件  
    *@return 删除成功返回 true，否则返回 false。  
    */  
   public static boolean DeleteFolder(String sPath) {   
       boolean flag = false;   
       File file = new File(sPath);   
       // 判断目录或文件是否存在   
       if (!file.exists()) {  // 不存在返回 false   
           return flag;   
       } else {   
           // 判断是否为文件   
           if (file.isFile()) {  // 为文件时调用删除文件方法   
               return FileUtil.deleteFile(sPath);   
           } else {  // 为目录时调用删除目录方法   
               return FileUtil.deleteDirectory(sPath);
           }   
       }   
   }
   
   /**  
    * 删除单个文件  
    * @param   sPath    被删除文件的文件名  
    * @return 单个文件删除成功返回true，否则返回false  
    */  
   public static boolean deleteFile(String sPath) {   
       boolean flag = false;   
       File file = new File(sPath);   
       // 路径为文件且不为空则进行删除   
       if (file.isFile() && file.exists()) {   
           file.delete();   
           flag = true;   
       }   
       return flag;   
   }
   
   /**  
    * 删除目录（文件夹）以及目录下的文件  
    * @param   sPath 被删除目录的文件路径  
    * @return  目录删除成功返回true，否则返回false  
    */  
   public static boolean deleteDirectory(String sPath) {   
       //如果sPath不以文件分隔符结尾，自动添加文件分隔符   
       if (!sPath.endsWith(File.separator)) {   
           sPath = sPath + File.separator;   
       }
       File dirFile = new File(sPath);   
       //如果dir对应的文件不存在，或者不是一个目录，则退出   
       if (!dirFile.exists() || !dirFile.isDirectory()) {   
           return false;   
       }
       boolean flag = true;   
       //删除文件夹下的所有文件(包括子目录)
       File[] files = dirFile.listFiles();   
       for (int i = 0; i < files.length; i++) {   
           //删除子文件   
           if (files[i].isFile()) {   
               flag = deleteFile(files[i].getAbsolutePath());   
               if (!flag) break;   
           } //删除子目录   
           else {   
               flag = deleteDirectory(files[i].getAbsolutePath());   
               if (!flag) break;   
           }   
       }   
       if (!flag) return false;   
       //删除当前目录   
       if (dirFile.delete()) {   
           return true;   
       } else {   
           return false;   
       }   
   }
   
   //Linux系统 路径地址转换
   public static String pathTransition(String path){
	    //Linux系统 
   		if(File.separator.equals("/")){     
	        return path.replaceAll("\\\\", "/");
   		}else {
			return path;
		}
   }
}
