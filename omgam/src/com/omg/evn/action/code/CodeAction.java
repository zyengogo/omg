package com.omg.evn.action.code;

import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import com.omg.evn.action.SystemConstants;
import com.omg.evn.entity.app.AppCode;
import com.omg.evn.entity.system.Sysuser;
import com.omg.evn.service.code.CodeService;
import com.omg.xbase.struts.Struts2Utils;
import com.omg.xbase.struts.WebUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @Company: OMG Technology co.Ltd. (c) copyright
 * @Title: CodeAction.java
 * @Package com.omg.evn.action.code
 * @discription 编码处理action
 * @author zyen
 * @date 2015-5-1 下午6:54:25 
 * @version V1.0 
 * @editor:
 * @editDate: 
 * @editInfo:
 */
public class CodeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Resource
	private CodeService codeService;
	private HttpServletRequest request;
	private HttpServletResponse response;

	private int pageNumber; // 当前页码
	private int pageSize; // 每页记录数

	private String codeName;
	private AppCode appCode;

	/**
	 * 
	 * @Description: 分页查询
	 * @author zyen
	 * @date 2015-5-1 下午4:44:18
	 * @param
	 * @return void
	 * @throws
	 */
	public void getPageList() {
		String str = codeService.getPageList(pageNumber, pageSize, codeName);
		Struts2Utils.renderJson(str);
	}

	/**
	 * 
	 * @Description: 新增编码
	 * @author zyen
	 * @date 2015-5-1 下午5:04:56
	 * @param
	 * @return void
	 * @throws
	 */
	public void addfrom() {
		String msg = "error";
		JSONObject jo = new JSONObject();
		
		Sysuser user = (Sysuser)WebUtils.getSession().getAttribute(SystemConstants.SYSTEM_USERINFO);
		
		if(user!=null){
			int userid=user.getId();
			if (appCode != null) {
				appCode.setCodeName(appCode.getCodeName());
				appCode.setCodeType(appCode.getCodeType());
				appCode.setUserId(userid);
				appCode.setCreateDate(new Date());
				appCode.setLastDate(new Date());
				try {
					codeService.saveEntity(appCode);
					msg = "ok";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		jo.put("msg", msg);
		Struts2Utils.renderJson(jo.toString());
	}

	/**
	 * 
	 * @Description: 删除
	 * @author zyen
	 * @date 2015-5-1 下午7:23:52 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void del() {
		String str = "";
		if (appCode != null) {
			try {
				int id = appCode.getCodeId();
				codeService.delEntityById(id);
				str = "ok";
			} catch (Exception e) {
				e.printStackTrace();
				str = "error";
			}
		}
		Struts2Utils.renderJson(str);
	}

	/**
	 * 
	 * @Description: 根据ID查询
	 * @author zyen
	 * @date 2015-5-1 下午7:27:08 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void findById() {
		if(appCode !=null){
			AppCode _appCode = codeService.findById(appCode.getCodeId());
			Struts2Utils.renderJson(_appCode);
		}
	}

	/**
	 * 
	 * @Description: 编辑保存
	 * @author zyen
	 * @date 2015-5-1 下午7:31:02 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void editSave() {
		String str = "";
		if (appCode != null) {
			try {
				AppCode _appCode = codeService.findById(appCode.getCodeId());
				_appCode.setCodeName(appCode.getCodeName());
				_appCode.setCodeType(appCode.getCodeType());
				_appCode.setLastDate(new Date());
				codeService.updateEntity(_appCode);
				str = "ok";
			} catch (Exception e) {
				e.printStackTrace();
				str = "error";
			}
		}
		Struts2Utils.renderJson(str);
	}

	// -------------------------------------------------------------------
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public AppCode getAppCode() {
		return appCode;
	}

	public void setAppCode(AppCode appCode) {
		this.appCode = appCode;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	

}
