package com.omg.evn.service.code;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.omg.evn.dao.BaseDao;
import com.omg.evn.entity.app.AppCode;
import com.omg.evn.util.sysutil.PojoMapper;


/**
 * 
 * @Company: OMG Technology co.Ltd. (c) copyright
 * @Title: CodeService.java
 * @Package com.omg.evn.service.code
 * @discription 编码查询
 * @author zyen
 * @date 2015-5-1 下午4:44:47 
 * @version V1.0 
 * @editor:
 * @editDate: 
 * @editInfo:
 */
@Service
@Transactional
public class CodeService {
	@Resource
	private BaseDao baseDao;
	
	/**
	 * 
	 * @Description: 获取某一页的数据
	 * @author zyen
	 * @date 2015-5-1 下午7:19:51 
	 * @param @param pageNumber
	 * @param @param pageSize
	 * @param @param codeName
	 * @param @return     
	 * @return String    
	 * @throws
	 */
	public String getPageList(int pageNumber,int pageSize,String codeName){
		String hqlData = "from AppCode where 1=1 ";
		String hqlCount = "select count(*) from AppCode  where 1=1 ";
		String condition = "";
		// 查询条件
		if (codeName != null && !"".equals(codeName.trim())&&!"undefined".equals(codeName.trim())) {			
			condition += " and codeName like '%" + codeName.trim() + "%'";
		}		
		hqlData  += condition;// 添加条件
		hqlCount += condition;
		hqlData  += " order by codeId desc";//排序
		
		List<AppCode> appCodeList = this.baseDao.findNowPageList(pageNumber, pageSize,hqlData);
		
		List list = new ArrayList();
		Map map = null;
		for(AppCode appCode : appCodeList){
			map = new HashMap();
			map.put("codeId", appCode.getCodeId());
			map.put("codeType", appCode.getCodeType());
			map.put("codeName", appCode.getCodeName());
			map.put("userId", appCode.getUserId());
			String createDate="";
			String lastDate="";
			if(appCode.getCreateDate() != null){
				if(appCode.getCreateDate().equals("")==false){
					SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					createDate = formatter3.format(appCode.getCreateDate());
				}
			}
			if(appCode.getLastDate() != null){
				if(appCode.getLastDate().equals("")==false){
					SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					lastDate = formatter3.format(appCode.getLastDate());
				}
			}
			map.put("createDate", createDate);
			map.put("lastDate", lastDate);
			list.add(map);
		}
		//总数
		int sum = this.baseDao.findCount(hqlCount);
		
		//将list数组转换成json对象
		String str = PojoMapper.toJsonArray(list, sum);
		return str;
	}
	/**
	 * 
	 * @param bean
	 * @throws Exception
	 */
	public void saveEntity(AppCode bean) throws Exception {		
		baseDao.create(bean);
	}
	/**
	 * 删除
	 * @param id
	 */
	public void delEntityById(int id){
		baseDao.delById(AppCode.class,id);
	}
	/**
	 * 根据id获取
	 * @param id
	 */
	public AppCode findById(int id){	
		AppCode o = (AppCode)baseDao.findById(AppCode.class,id);
		return o;
	}
	/**
	 * 修改
	 * @param o
	 */
	public void updateEntity(AppCode o){		
		baseDao.update(o);;		
	}
}
