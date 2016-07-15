package com.omg.evn.util.sysutil;

import javax.servlet.http.HttpServletRequest;
/**
 * 获取ip
 * @author pcp
 *
 */
public class GetIP {

	public static String getIpAddr(HttpServletRequest request) {      
	       String ip = request.getHeader("x-forwarded-for");      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getHeader("Proxy-Client-IP");      
	       }      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getHeader("WL-Proxy-Client-IP");      
	       }      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getRemoteAddr();      
	       }      
	       return ip;      
	   }
	
	
	 public static long ipToLong(String strIp){
         long[] ip = new long[4];
         //先找到IP地址字符串中.的位置
         int position1 = strIp.indexOf(".");
         int position2 = strIp.indexOf(".", position1 + 1);
         int position3 = strIp.indexOf(".", position2 + 1);
         //将每个.之间的字符串转换成整型
         ip[0] = Long.parseLong(strIp.substring(0, position1));
         ip[1] = Long.parseLong(strIp.substring(position1+1, position2));
         ip[2] = Long.parseLong(strIp.substring(position2+1, position3));
         ip[3] = Long.parseLong(strIp.substring(position3+1));
         return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
     }
	 /*
	  * 
	  */
	 public static long ipStrToLong(String strIp){
        
		 long[] ip = new long[4];
		 
         String[] ips = strIp.split("\\.");
         
        
         
         ip[0] = Long.parseLong(ips[0]);
         ip[1] = Long.parseLong(ips[1]);
         ip[2] = Long.parseLong(ips[2]);
         ip[3] = Long.parseLong(ips[3]);
        
        
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
     }
}
