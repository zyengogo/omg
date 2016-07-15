/**
 * 
 */
package com.omg.evn.util.sysutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.omg.evn.util.fileutil.ReadProperties;

/**
 * @author Administrator
 *
 */
public class FileCopyUtility {

	//设置默认通道大小
	private static int channelsize = 1000000;//2M;
	public FileCopyUtility() {
	}
	
	public static void main(String[] args) {
		try {
			 new FileCopyUtility().copyFileAction( new File("C:\\TEMP\\新闻制作\\测试1\\NleConfig.dat"), new File("d:\\TEMP\\新闻制作\\测试1\\NleConfig.dat"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 public  static Map copyFileAction(File f1,File f2) throws Exception{
		 	Map resultMap = new TreeMap();
	        long time = new Date().getTime();
	        int length = channelsize;
	        FileInputStream inFile = new FileInputStream(f1);
	        FileOutputStream outFile = new FileOutputStream(f2);
	        FileChannel inChannel = inFile.getChannel();
	        FileChannel outChannel = outFile.getChannel();
	        ByteBuffer b = null;
	        while(true){
	            if(inChannel.position() == inChannel.size()){
	                inChannel.close();
	                outChannel.close();
	                resultMap.put("message", "succeed");
	                resultMap.put("time", new Long(new Date().getTime()-time));
	                return resultMap;
	            }
	            if((inChannel.size() - inChannel.position())<length){
	                length = (int)(inChannel.size() - inChannel.position());
	            }else
	                length = channelsize;
	            b = ByteBuffer.allocateDirect(length);
	            inChannel.read(b);
	            b.flip();
	            outChannel.write(b);
	            outChannel.force(false);
	        }
	    }

		public static Map copyStrutsTempFile(File fromFile , String toFileName,String ModelName) throws Exception{
			//如果文件夹不存在创建
			 File tempFile = new File(ReadProperties.getPoperitesValueByKey("imageRootPath") + "/" + ModelName );
			 
			 if(!tempFile.exists())tempFile.mkdirs();
			
			String toFilePath = tempFile +"/"+ toFileName+ReadProperties.getPoperitesValueByKey("imageType") ;
			Map map = copyFileAction(fromFile, new File(toFilePath));
			return map;
		}
		
		//设置默认通道大小	
		public static void copyFileAction2(File f1,File f2) throws Exception{
		    long time = new Date().getTime();
		    int length = channelsize;
		    FileInputStream inFile = new FileInputStream(f1);
		    FileOutputStream outFile = new FileOutputStream(f2);
		    FileChannel inChannel = inFile.getChannel();
		    FileChannel outChannel = outFile.getChannel();
		    ByteBuffer b = null;
		    while(true){
		        if(inChannel.position() == inChannel.size()){
		            inChannel.close();
		            outChannel.close();
		        }
		        if((inChannel.size() - inChannel.position())<length){
		            length = (int)(inChannel.size() - inChannel.position());
		        }else
		            length = channelsize;
		        b = ByteBuffer.allocateDirect(length);
		        inChannel.read(b);
		        b.flip();
		        outChannel.write(b);
		        outChannel.force(false);
		    }
		}
		
		public static void deleteOldPath(String path){
			//   /ppmsImage/location/25_1294203595489.png
			//    /ppms/images/nopic.gif
			//   c:/ppmsImage/
			if("".equals(path) || path.indexOf("ppmsImage") < 0){
				return;
			}else{
				path = path.replace("/ppmsImage/",ReadProperties.getPoperitesValueByKey("imageRootPath"));
			}
			File  file=new File(path);
			if(file.exists()){
				file.delete();
			}
		}
}
