package com.omg.evn.action.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.omg.evn.entity.appenddix.Appendix;
import com.omg.evn.service.upload.AppendixService;
import com.omg.evn.util.fileutil.FileUploadUtil;
import com.omg.evn.util.fileutil.FileUtil;
import com.omg.evn.util.sysutil.PojoMapper;
import com.omg.xbase.struts.Struts2Utils;
import com.omg.xbase.xlog.Log;
import com.omg.xbase.xlog.LogFactory;
import com.opensymphony.xwork2.ActionSupport;



/**
 * 多文件上传Action
 * @author: wt
 * @Description: TODO() 
 * @JDK version:  jdk1.6 
 */
@Results({
	@Result(name = "success", type="dispatcher",params={"location","${actionMsg}"}),
	@Result(name = "zyk", location = "/WEB-INF/content/mwzyk/zyk/index.jsp"),
	@Result(name = "fail", location = "/WEB-INF/content/mwjc/index.jsp")
})
public class UploadAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private Log log = LogFactory.getLogger();
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/** 文件对象 */ 
	private List<File> Filedata;       
	/** 文件名 */ 
	private List<String> FiledataFileName;       
	/** 文件内容类型 */ 
	private List<String> FiledataContentType;  
	/** 返回字符串 */
	private String returnValue = null;
	
	@Resource
	private AppendixService appendixService;
	private int zykid;	    // 关联资源库数据id
	private String zyktype;	// 资源库类型
	private String timestamp;	// 资源库类型
	
	private int id;	    //
	
	/**
	 * 页面跳转
	 * @Description: TODO() 
	 * @return
	 */
	public String jump(){
		return "zyk";
	}
	
	/**
	 * 根据s数据id 资源库类型 获取所有附件
	 * @Description: TODO() 
	 * @return
	 */
	public String findAppendixById(){
		String str = "";
		HttpSession session = request.getSession();
		try {
			Integer tempZykid = (Integer) session.getAttribute("zykid");
			String  tempZyktype = (String) session.getAttribute("zyktype");
			String  tempTimestamp = (String) session.getAttribute("timestamp");
			if (tempZykid != null && tempZyktype != null && !"".equals(tempZykid) && !"".equals(tempZyktype)) {
				if (zykid ==tempZykid && (zyktype+"").equals(tempZyktype) && (timestamp+"").equals(tempTimestamp)) {
					//System.out.println("从session中获取:"+tempTimestamp+",zyktype:"+zyktype+",zykid:"+zykid);
					//从session中获取数据
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("file", session.getAttribute("filelist"));
					dataMap.put("img",  session.getAttribute("imglist"));
					dataMap.put("video", session.getAttribute("videolist"));
					dataMap.put("class", session.getAttribute("classlist"));//课件
					dataMap.put("content", session.getAttribute("contentlist"));//文章中的图片
					//将list数组转换成json对象
					str = PojoMapper.toJson(dataMap, false);
				}else {
					//System.out.println("数据库获取1"+tempTimestamp+",zyktype:"+zyktype+",zykid:"+zykid);
					str = appendixService.findAppendixById(zykid, zyktype,session);
					session.setAttribute("appendixIsChange", false);//附件默认未修改
				}
			}else {
				//System.out.println("数据库获取2"+tempTimestamp+",zyktype:"+zyktype+",zykid:"+zykid);
				str = appendixService.findAppendixById(zykid, zyktype,session);
				session.setAttribute("appendixIsChange", false);//附件默认未修改
			}
			session.setAttribute("zykid", zykid);
			session.setAttribute("zyktype", zyktype);
			session.setAttribute("timestamp", timestamp);
			
		} catch (Exception e) {
			e.printStackTrace();
			str = "error";
			log.error("#UploadAction>>findAppendixById():"+e);
		}
		Struts2Utils.renderJson(str);
		return null;
	}
	/**
	 * 根据s数据id 资源库类型 获取所有附件
	 * @Description: TODO() 
	 * @return
	 */
	public String findAppendixByIdNew(){
		String str = "";
		HttpSession session = request.getSession();
		try {
			Integer tempZykid = (Integer) session.getAttribute("zykid");
			String  tempZyktype = (String) session.getAttribute("zyktype");
			String  tempTimestamp = (String) session.getAttribute("timestamp");
			if (tempZykid != null && tempZyktype != null && !"".equals(tempZykid) && !"".equals(tempZyktype)) {
				if (zykid ==tempZykid && (zyktype+"").equals(tempZyktype) && (timestamp+"").equals(tempTimestamp)) {
					//System.out.println("从session中获取:"+tempTimestamp+",zyktype:"+zyktype+",zykid:"+zykid);
					//从session中获取数据
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("file", session.getAttribute("filelist"));
					dataMap.put("img",  session.getAttribute("imglist"));
					dataMap.put("video", session.getAttribute("videolist"));
					dataMap.put("class", session.getAttribute("classlist"));//课件
					dataMap.put("content", session.getAttribute("contentlist"));//文章中的图片
					//将list数组转换成json对象
					str = PojoMapper.toJson(dataMap, false);
				}else {
					//System.out.println("数据库获取1"+tempTimestamp+",zyktype:"+zyktype+",zykid:"+zykid);
					str = appendixService.findAppendixByIdNew(zykid, zyktype,session);
					session.setAttribute("appendixIsChange", false);//附件默认未修改
				}
			}else {
				//System.out.println("数据库获取2"+tempTimestamp+",zyktype:"+zyktype+",zykid:"+zykid);
				str = appendixService.findAppendixByIdNew(zykid, zyktype,session);
				session.setAttribute("appendixIsChange", false);//附件默认未修改
			}
			session.setAttribute("zykid", zykid);
			session.setAttribute("zyktype", zyktype);
			session.setAttribute("timestamp", timestamp);
			
		} catch (Exception e) {
			e.printStackTrace();
			str = "error";
			log.error("#UploadAction>>findAppendixById():"+e);
		}
		Struts2Utils.renderJson(str);
		return null;
	}
	
	/**
	 * 保存附件（保存至session 未保存数据库）
	 * @Description: TODO() 
	 * @return
	 */
	public String saveAppendix(){
		String str = "";
		try {
			String imgData = request.getParameter("imgData");
			String fileData = request.getParameter("fileData");
			String videoData = request.getParameter("videoData");
			String classData = request.getParameter("classData");//课件附件
			//String videoimgData = request.getParameter("videoimgData");
			appendixService.saveAppendix(zykid, zyktype, imgData, fileData, videoData,classData,request);
			str = "ok";
		} catch (Exception e) {
			e.printStackTrace();
			str = "error";
			log.error("#UploadAction>>findAppendixById():"+e);
		}
		Struts2Utils.renderJson(str);
		return null;
	}
	
	/**
	 * 文件上传
	 * @Description: TODO() 
	 * @return
	 * @throws Exception
	 */
	public String upload() throws Exception{
		String webappsPath  = FileUploadUtil.getWebappsPath();//eg:E://.../webapps
		String saveFileTempPath = FileUploadUtil.getSaveFileTempPath();//获取上传临时文件路径eg:/upload/temp/20140808/
		
		List<String> successPathList = new ArrayList<String>();
		SimpleDateFormat fileNameSdf = new SimpleDateFormat("yyyyMMdd-HHmmssSSS");
        
		if (Filedata != null){
			int i = 0;
			for (; i < Filedata.size(); i++){
				FileInputStream is = new FileInputStream(Filedata.get(i));
				String fileExtension = FileUtil.getExtensionName(FiledataFileName.get(i));//获取文件扩展名
				String fileSaveNameString = fileNameSdf.format(new Date())+"."+fileExtension;//文件保存名
				FileOutputStream os = new FileOutputStream(webappsPath + saveFileTempPath + fileSaveNameString);
				System.out.println("文件名："+FiledataFileName.get(i)+"，保存临时文件："+saveFileTempPath + fileSaveNameString);
				byte buffer[] = new byte[8192];
				int count = 0;
				while ((count = is.read(buffer)) > 0){
					os.write(buffer, 0, count);
				}
				os.close();
				is.close();
				successPathList.add(saveFileTempPath + fileSaveNameString);
			}
			returnValue ="success";
			//return SUCCESS;
		}else{
			returnValue ="fail";
			//return INPUT;
		}
		Struts2Utils.renderJson(successPathList);
		return null;
	}
	
	/*********************民族成份更改系统mzcf/资金申报系统/evn/dec/pay-开始**********************************/
	/**
	 * 保存附件 保存数据库
	 * @Description: TODO() 
	 * @return
	 */
	public String saveAppendixFile(){
		String str = "";
		try {
			//String imgData = request.getParameter("imgData");
			String fileData = request.getParameter("fileData");
			//String videoData = request.getParameter("videoData");
			//String classData = request.getParameter("classData");//课件附件
			//String videoimgData = request.getParameter("videoimgData");
			appendixService.saveAppendixFile(zykid, zyktype, fileData,request);
			str = "ok";
		} catch (Exception e) {
			e.printStackTrace();
			str = "error";
			log.error("#UploadAction>>findAppendixById():"+e);
		}
		Struts2Utils.renderJson(str);
		return null;
	}
	
	/**
	 * 保存附件 保存数据库
	 * @Description: TODO() 
	 * @return
	 */
	public String deleteAppendixById(){
		String str = "";
		try {
			HttpSession session = request.getSession();
			List<Appendix> filelist = (List<Appendix>) session.getAttribute("filelist");//new ArrayList();
			for (Appendix appendix : filelist) {
				if (appendix.getId() == id) {
					filelist.remove(appendix);
					break;
				}
			}
			session.setAttribute("filelist",filelist);
			session.setAttribute("appendixIsChange", true);//附件已修改
			str = "ok";
		} catch (Exception e) {
			e.printStackTrace();
			str = "error";
			log.error("#UploadAction>>findAppendixById():"+e);
		}
		Struts2Utils.renderJson(str);
		return null;
	}
	
	/*********************民族成份更改系统mzcf/资金申报系统/evn/dec/pay-结束**********************************/
	
	
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	
	public List<File> getFiledata() {   
	      return Filedata;   
	}
	
	public void setFiledata(List<File> filedata) {   
	      Filedata = filedata;   
	}   

	public List<String> getFiledataFileName() {   
	      return FiledataFileName;   
	}   

	public void setFiledataFileName(List<String> filedataFileName) {   
	      FiledataFileName = filedataFileName;   
	}  

	public List<String> getFiledataContentType() {   
	      return FiledataContentType;   
	}   
	
	public void setFiledataContentType(List<String> filedataContentType) {   
	      FiledataContentType = filedataContentType;   
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public AppendixService getAppendixService() {
		return appendixService;
	}

	public void setAppendixService(AppendixService appendixService) {
		this.appendixService = appendixService;
	}

	public int getZykid() {
		return zykid;
	}

	public void setZykid(int zykid) {
		this.zykid = zykid;
	}

	public String getZyktype() {
		return zyktype;
	}

	public void setZyktype(String zyktype) {
		this.zyktype = zyktype;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}