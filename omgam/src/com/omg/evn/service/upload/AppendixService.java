package com.omg.evn.service.upload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.omg.evn.dao.BaseDao;
import com.omg.evn.entity.appenddix.Appendix;
import com.omg.evn.service.baseservice.BaseService;
import com.omg.evn.util.fileutil.FileUploadUtil;
import com.omg.evn.util.fileutil.FileUtil;
import com.omg.evn.util.sysutil.PojoMapper;
import com.omg.xbase.struts.Struts2Utils;
import com.omg.xbase.xlog.*;

 

/**
 * 附件管理Service
 * @author: wt
 * @Description: TODO() 
 * @JDK version:  jdk1.6 
 */
@Service
public class AppendixService extends BaseService{
	
	private Log log = LogFactory.getLogger();

	@Resource
	private BaseDao baseDao;
	
	/**
	 * 删除文章中的附件
	 * @Description: TODO() 
	 * @param zykid
	 * @param zyktype
	 * @return
	 */
	public String deleteAppendixContent(int zykid,String zyktype){
		String str = "";
		//删除所有相关附件
		baseDao.bulkUpdate("delete from MwZykAppendix where zykid='"+zykid+"' and zyktype='"+zyktype+"' and type in (8)");//删除文章中的图片附件
		return str;
	}
	
	/**
	 * 批量删除所有相关的附件
	 * @Description: TODO() 
	 * @param zykid
	 * @param zyktype
	 * @return
	 */
	public String deleteAllAppendixContent(String zykids,String zyktype){
		String str = "";
		//删除所有相关附件
		if (zykids == null || "".equals(zykids)) zykids = "''";
		baseDao.bulkUpdate("delete from MwZykAppendix where zykid in('"+zykids+"') and zyktype='"+zyktype+"'");//删除文章中的图片附件
		return str;
	}
	
	/**
	 * 修改文章中的附件
	 * @Description: TODO() 
	 * @param content
	 * @return
	 */
	public String updateAppendixFromContent(String content,int zykid,String zyktype){
		String str = (content == null)?"":content;
		//删除所有相关附件
		//baseDao.bulkUpdate("delete from MwZykAppendix where zykid='"+zykid+"' and zyktype='"+zyktype+"' and type in (8)");//删除文章中的图片附件
		
		String searchImgReg = "(?x)(src|SRC|href|HREF)=('|\")(/?([\\w-]+/)*([\\w-]+\\.(jpg|JPG|png|PNG|gif|GIF)))('|\")";
		
	    Matcher urlMatcher = Pattern.compile(searchImgReg).matcher(str);
        List<String> urlList = new ArrayList<String>();
        while (urlMatcher.find()) {
        	String tempUrl = urlMatcher.group(3);
        	//不存在再添加 过滤相同url
        	if (!urlList.contains(tempUrl)) {
        		urlList.add(tempUrl);
			}
        }
        
        //构建保存路径
		String path = AppendixService.class.getResource("/").getPath().toString().replaceAll("%20", " ");//类路径
        String fileBasePath = path.substring(0, path.indexOf("webapps"))+"webapps";//webapps 路径
        SimpleDateFormat yyyyMMsdf = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat yyyyMMddsdf = new SimpleDateFormat("yyyyMMdd");
        String yyyyMMStr   = yyyyMMsdf.format(new Date());  //获取yyyyMM格式时间
        String yyyyMMddStr = yyyyMMddsdf.format(new Date());//获取yyyyMMdd格式时间
        String saveFilePath = fileBasePath+"/upload/appendix/"+yyyyMMStr+"/"+yyyyMMddStr+"/";//正式目录
        String saveBaseUrl = "/upload/appendix/"+yyyyMMStr+"/"+yyyyMMddStr+"/";//正式目录;
        FileUtil.createDir(saveFilePath);//创建目录
        
        for (int i = 0; i < urlList.size(); i++) {
			String tempUrl = urlList.get(i);
			System.out.println("处理："+tempUrl);
			Appendix appendix = new Appendix();
			appendix.setId(null);
			appendix.setZykid(zykid);
			appendix.setZyktype(zyktype);
			appendix.setUrl(tempUrl);
			appendix.setType("8");//文章中的图片附件
			appendix.setOrderid(i);//排序id
			this.saveContentFile(appendix,fileBasePath,saveFilePath,saveBaseUrl);//修改路径 拷贝文件至正式路径
			baseDao.save(appendix);
			content = content.replaceAll(tempUrl, appendix.getUrl());//修改文章的路径
		}
        return content;
	}
	
	public void saveContentFile(Appendix appendix,String fileBasePath,String saveFilePath,String saveBaseUrl){
		//附件路径处理 存在临时文件才复制
		String url = appendix.getUrl();
		String filePath = "";
		if (url != null && !"".equals(url) && url.startsWith("/upload/jsp")) {
			filePath = fileBasePath + url;
			File file = new File(filePath);
			if (file.exists()) {
				try {
					FileUtils.copyFileToDirectory(file, new File(saveFilePath));
					appendix.setUrl(saveBaseUrl + url.substring(url.lastIndexOf("/")+1, url.length()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 根据数据id 资源库类型 获取所有附件
	 * @Description: TODO() 
	 * @param pageNumber 当前页码
	 * @param pageSize   每页记录数
	 * @param classId
	 * @param name
	 * @return
	 */
	public String findAppendixById(int zykid,String zyktype,HttpSession session)  throws Exception {
		// 资源库类型 "zyk":普通资源库的附件，"teacher":师资信息库 "cadres":干部人才资源库
		String hql_data  = "from MwZykAppendix where 1=1";//拼装获取数据的hql语句
		String whereStr = "";//条件语句
        String orderByStr = "";//排序
        
		whereStr += " and zykid=" + zykid;
		whereStr += " and zyktype='" + zyktype+"'";
		orderByStr = " order by orderid desc";
		hql_data = hql_data + whereStr + orderByStr;
		System.out.println("hql_data:"+hql_data);
		List<Appendix> appendixList = baseDao.findByHQL(hql_data);
		//private String  type;		// 附件类型 0文件 1图片 2视频
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List filelist = new ArrayList();
		List imglist  = new ArrayList();
		List videolist = new ArrayList();
		List classlist = new ArrayList();
		List contentlist = new ArrayList();
		for (int i = 0; i < appendixList.size(); i++) {
			Appendix appendix = appendixList.get(i);
			String type = appendix.getType();
			//0文件
			if ("0".equals(type)) {
				filelist.add(appendix);
			//1图片	
			}else if("1".equals(type)){
				imglist.add(appendix);
			//2视频	
			}else if("2".equals(type)){
				videolist.add(appendix);
			//3课件
			}else if("3".equals(type)){
				classlist.add(appendix);
			//8文章中的附件
			}else if("8".equals(type)){
				contentlist.add(appendix);
			}
		}
		dataMap.put("file", filelist);
		dataMap.put("img", imglist);
		dataMap.put("video", videolist);
		dataMap.put("class", classlist);
		dataMap.put("content", contentlist);
		session.setAttribute("filelist",filelist);//查询后放入session
		session.setAttribute("imglist",imglist);//查询后放入session
		session.setAttribute("videolist",videolist);//查询后放入session
		session.setAttribute("classlist",classlist);//查询后放入session
		session.setAttribute("contentlist",contentlist);//查询后放入session
		
		//将list数组转换成json对象
		String str = PojoMapper.toJson(dataMap, false);
		return str;
	}
	
	public String findAppendixByIdNew(int zykid,String zyktype,HttpSession session)  throws Exception {
		// 资源库类型 "zyk":普通资源库的附件，"teacher":师资信息库 "cadres":干部人才资源库
		String hql_data  = "from MwXxsbAppendix where 1=1";//拼装获取数据的hql语句
		String whereStr = "";//条件语句
		String orderByStr = "";//排序
		
		whereStr += " and zykid=" + zykid;
		whereStr += " and zyktype='" + zyktype+"'";
		orderByStr = " order by orderid desc";
		hql_data = hql_data + whereStr + orderByStr;
		System.out.println("hql_data:"+hql_data);
		List<Appendix> appendixList = baseDao.findByHQL(hql_data);
		//private String  type;		// 附件类型 0文件 1图片 2视频
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List filelist = new ArrayList();
		List imglist  = new ArrayList();
		List videolist = new ArrayList();
		List classlist = new ArrayList();
		List contentlist = new ArrayList();
		for (int i = 0; i < appendixList.size(); i++) {
			Appendix appendix = appendixList.get(i);
			String type = appendix.getType();
			//0文件
			if ("0".equals(type)) {
				filelist.add(appendix);
				//1图片	
			}else if("1".equals(type)){
				imglist.add(appendix);
				//2视频	
			}else if("2".equals(type)){
				videolist.add(appendix);
				//3课件
			}else if("3".equals(type)){
				classlist.add(appendix);
				//8文章中的附件
			}else if("8".equals(type)){
				contentlist.add(appendix);
			}
		}
		dataMap.put("file", filelist);
		dataMap.put("img", imglist);
		dataMap.put("video", videolist);
		dataMap.put("class", classlist);
		dataMap.put("content", contentlist);
		session.setAttribute("filelist",filelist);//查询后放入session
		session.setAttribute("imglist",imglist);//查询后放入session
		session.setAttribute("videolist",videolist);//查询后放入session
		session.setAttribute("classlist",classlist);//查询后放入session
		session.setAttribute("contentlist",contentlist);//查询后放入session
		
		//将list数组转换成json对象
		String str = PojoMapper.toJson(dataMap, false);
		return str;
	}
	
	/**
	 * 保存附件（保存至session 未保存数据库）
	 * @Description: TODO() 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String saveAppendix(int zykid,String zyktype,String imgData,String fileData,String videoData,String classData,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		//资源库 普通
		if (fileData!=null && !"".equals(fileData)) {
			List<Appendix> filelist = getListFromJsonString(fileData, zykid, zyktype);
			session.setAttribute("filelist", filelist);//放入session
		}
		if (imgData!=null && !"".equals(imgData)) {
			List<Appendix> imglist = getListFromJsonString(imgData, zykid, zyktype);
			session.setAttribute("imglist",  imglist); //放入session
		}
		if (videoData!=null && !"".equals(videoData)) {
			List<Appendix> videolist = getListFromJsonString(videoData, zykid, zyktype);
			session.setAttribute("videolist", videolist);//放入session	
		}
		if (classData!=null && !"".equals(classData)) {
			List<Appendix> classlist = getListFromJsonString(classData, zykid, zyktype);
			session.setAttribute("classlist", classlist);//放入session
		}
		
		session.setAttribute("appendixIsChange", true);//附件是否改变
		session.setAttribute("zykid", zykid);
		session.setAttribute("zyktype", zyktype);
		
		return "ok";
	}
	
	/**
	 * 保存附件进数据库
	 * @Description: TODO() 
	 * @param zykid
	 * @param zyktype
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String saveAppendixToDB(int zykid,String zyktype,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		boolean appendixIsChange = false;//附件是否改变
		if(session.getAttribute("appendixIsChange")!=null){
			appendixIsChange = (Boolean) session.getAttribute("appendixIsChange");//附件是否改变
		}	
		
		Integer tempZykid = (Integer) session.getAttribute("zykid");
		String  tempZyktype = (String) session.getAttribute("zyktype");
		String  tempTimestamp = (String) session.getAttribute("timestamp");
		
		String str = "";
		//附件有修改时 修改附件操作 并且id和类型匹配时修改
		if (appendixIsChange) {
			//新增 或修改时
			if ((tempZykid != null && tempZykid == 0) || (zykid == tempZykid && (zyktype+"").equals(tempZyktype))){
				
				//删除所有相关附件
				baseDao.bulkUpdate("delete from MwZykAppendix where zykid='"+zykid+"' and zyktype='"+zyktype+"' and type in (0,1,2,3)");//删除文件、图片、视频 课件
				
				List<Appendix> filelist = (List<Appendix>) session.getAttribute("filelist");//获取session
				List<Appendix> imglist = (List<Appendix>) session.getAttribute("imglist");//获取session
				List<Appendix> videolist = (List<Appendix>) session.getAttribute("videolist");//获取session
				List<Appendix> classlist = (List<Appendix>) session.getAttribute("classlist");//获取session
				
				//构建保存路径
		        String webappsPath = FileUploadUtil.getWebappsPath();//eg:E://.../webapps
		        String saveBaseUrl = FileUploadUtil.getSaveFileRealPath();//eg: /upload/appendix/201408/20140808/
		        String saveFilePath = webappsPath + saveBaseUrl;//正式目录
		        FileUtil.createDir(saveFilePath);//创建目录
		        
				for (int i = 0; i < filelist.size(); i++) {
					Appendix appendix = filelist.get(i);
					appendix.setId(null);
					appendix.setZykid(zykid);
					appendix.setZyktype(zyktype);
					appendix.setOrderid(i);//排序id
					copyFile(appendix,webappsPath,saveFilePath,saveBaseUrl);//修改路径 拷贝文件至正式路径
					baseDao.save(appendix);
				}
				for (int i = 0; i < imglist.size(); i++) {
					Appendix appendix = imglist.get(i);
					appendix.setId(null);
					appendix.setZykid(zykid);
					appendix.setZyktype(zyktype);
					appendix.setOrderid(i);//排序id
					copyFile(appendix,webappsPath,saveFilePath,saveBaseUrl);//修改路径 拷贝文件至正式路径
					baseDao.save(appendix);
				}
				for (int i = 0; i < videolist.size(); i++) {
					Appendix appendix = videolist.get(i);
					appendix.setId(null);
					appendix.setZykid(zykid);
					appendix.setZyktype(zyktype);
					appendix.setOrderid(i);//排序id
					copyFile(appendix,webappsPath,saveFilePath,saveBaseUrl);//修改路径 拷贝文件至正式路径
					baseDao.save(appendix);
				}
				for (int i = 0; i < classlist.size(); i++) {
					Appendix appendix = classlist.get(i);
					appendix.setId(null);
					appendix.setZykid(zykid);
					appendix.setZyktype(zyktype);
					appendix.setOrderid(i);//排序id
					copyFile(appendix,webappsPath,saveFilePath,saveBaseUrl);//修改路径 拷贝文件至正式路径
					baseDao.save(appendix);
				}
			}
			//将list数组转换成json对象
			//str = PojoMapper.toJson(dataMap, false)
		}
		return str;
	}
	
	/**
	 * 拷贝文件至正式路径
	 * @Description: TODO() 
	 * @param appendix
	 */
	public void copyFile(Appendix appendix,String fileBasePath,String saveFilePath,String saveBaseUrl){
		String url = appendix.getUrl();
		String videoimageurl = appendix.getVideoimageurl();
		String filePath = "";
		
		//附件路径处理 存在临时文件才复制
		if (url != null && !"".equals(url) && url.indexOf("temp")>-1) {
			filePath = fileBasePath + url;
			File file = new File(filePath);
			if (file.exists()) {
				try {
					FileUtils.copyFileToDirectory(file, new File(saveFilePath));
					appendix.setUrl(saveBaseUrl + url.substring(url.lastIndexOf("/")+1, url.length()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//视频图片路径处理 存在临时文件时才复制
		if (videoimageurl != null && !"".equals(videoimageurl) && videoimageurl.indexOf("temp")>-1) {
			filePath = fileBasePath + videoimageurl;
			File file = new File(filePath);
			if (file.exists()) {
				try {
					FileUtils.copyFileToDirectory(file, new File(saveFilePath));
					appendix.setVideoimageurl(saveBaseUrl + videoimageurl.substring(videoimageurl.lastIndexOf("/")+1, videoimageurl.length()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 从json字符串获取数据
	 * @Description: TODO() 
	 * @param jsonData
	 * @param zykid
	 * @param zyktype
	 * @return
	 */
	public List getListFromJsonString(String jsonData,int zykid,String zyktype){
		List<Appendix> list = new ArrayList<Appendix>();
		JSONArray results = new JSONArray(jsonData);
		if(results.length()>0){
			for (int i = 0; i < results.length(); i++) {
				JSONObject result = results.getJSONObject(i);
				//System.out.println(result.getString("id"));
				String  id = result.getString("id");			// 附件ID
				String  name = result.getString("name");		// 附件名称
				String  type = result.getString("type");		// 附件类型 0文件 1图片 2视频
				String  videoimageurl = result.getString("videoimageurl");	// 相关视频图片 (视频相关图片,才有):
				String  url = result.getString("url");		// 附件URL
				int orderid = 0;// 排序
				try {
					orderid = result.getInt("orderid");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("类型转换出错！");
				}
				//int zykid = result.getInt("zykid");;	    // 关联资源库数据id
				//String  zyktype = result.getString("zyktype");
				//Integer formid = result.getString("formid");		// 当前数据所属分类
				//Integer classid = result.getString("classid");	// 所属6大类 分类ID
				//转换为实体bean
				Appendix appendix = new Appendix();
				//主键随机生成
				if ("0".equals(type)) {
					appendix.setId(100+i);
				}else if ("1".equals(type)){
					appendix.setId(200+i);
				}else if ("2".equals(type)){
					appendix.setId(300+i);
				}else if ("3".equals(type)){
					appendix.setId(400+i);
				}else if ("8".equals(type)){
					appendix.setId(500+i);
				}else {
					appendix.setId(600+i);
				}
				appendix.setName(name==null?"":name);
				appendix.setType(type==null?"":type);
				appendix.setUrl(url==null?"":url);
				appendix.setVideoimageurl(videoimageurl==null?"":videoimageurl);
				appendix.setOrderid(orderid);
				appendix.setZykid(zykid);
				appendix.setZyktype(zyktype);
				list.add(appendix);
			}
		}
		return list;
	}
	
	/*********************民族成份更改系统mzcf/资金申报系统/evn/dec/pay-开始**********************************/
	
	/**
	 * 保存附件 保存数据库
	 * @Description: TODO() 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String saveAppendixFile(int zykid,String zyktype,String fileData,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		//1.附件获取
		if (fileData!=null && !"".equals(fileData)) {
			List<Appendix> filelist = getListFromJsonString(fileData, zykid, zyktype);
			session.setAttribute("filelist", filelist);//放入session
		}
		session.setAttribute("appendixIsChange", true);//附件是否改变
		session.setAttribute("zykid", zykid);
		session.setAttribute("zyktype", zyktype);
		String str = "ok";
		return str;
	}
	
	/**
	 * 保存附件进数据库
	 * @Description: TODO() 
	 * @param zykid
	 * @param zyktype
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String saveAppendixFileToDB(int zykid,String zyktype) throws Exception {
		HttpSession session = Struts2Utils.getSession();
		//2.保存数据库
		//HttpSession session = request.getSession();
		boolean appendixIsChange = false;//附件是否改变
		if(session.getAttribute("appendixIsChange") != null){
			appendixIsChange = (Boolean) session.getAttribute("appendixIsChange");//附件是否改变
		}
		Integer tempZykid = (Integer) session.getAttribute("zykid");
		String  tempZyktype = (String) session.getAttribute("zyktype");
		String  tempTimestamp = (String) session.getAttribute("timestamp");
		
		String str = "";
		//附件有修改时 修改附件操作 并且id和类型匹配时修改
		if (appendixIsChange) {
			//新增 或修改时
			if ((tempZykid != null && tempZykid == 0) || (zykid == tempZykid && (zyktype+"").equals(tempZyktype))){
				//附件文件删除
				/*List <MwZykAppendix> appendixs = baseDao.findByHQL("from MwZykAppendix where zykid='"+zykid+"' and zyktype='"+zyktype+"' and type in (0,1,2,3)");
				if (appendixs != null && appendixs.size() > 0) {
					String webappsPath = FileUploadUtil.getWebappsPath();
					for (MwZykAppendix appendix : appendixs) {
						FileUtil.deleteFile(webappsPath + appendix.getUrl());
						//System.out.println("删除附件文件："+webappsPath + appendix.getUrl());
					}
				}*/
				//删除所有相关附件
				baseDao.bulkUpdate("delete from MwZykAppendix where zykid='"+zykid+"' and zyktype='"+zyktype+"' and type in (0,1,2,3)");//删除文件、图片、视频 课件
				
				List<Appendix> filelist = (List<Appendix>) session.getAttribute("filelist");//获取session
				
				//构建保存路径
		        String webappsPath = FileUploadUtil.getWebappsPath();//eg:E://.../webapps
		        String saveBaseUrl = FileUploadUtil.getSaveFileRealPath();//eg: /upload/appendix/201408/20140808/
		        String saveFilePath = webappsPath + saveBaseUrl;//正式目录
		        FileUtil.createDir(saveFilePath);//创建目录
		        
				for (int i = 0; i < filelist.size(); i++) {
					Appendix appendix = filelist.get(i);
					appendix.setId(null);
					appendix.setZykid(zykid);
					appendix.setZyktype(zyktype);
					appendix.setOrderid(i);//排序id
					copyFile(appendix,webappsPath,saveFilePath,saveBaseUrl);//修改路径 拷贝文件至正式路径
					baseDao.save(appendix);
				}
			}
			str = "ok";
		}
		return str;
	}
	/*********************民族成份更改系统mzcf/资金申报系统/evn/dec/pay-结束**********************************/
}
