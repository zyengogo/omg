package com.omg.evn.util.strutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
/**
 * 
 * @author zyen
 *
 */
public class StrUtil {
	private static final char SEPARATOR = '|';
	
	
	/**
	* JAVA从指定位置开始截取指定长度的字符串
	* @param input 输入字符串
	* @param index 截取位置，左侧第一个字符索引值是1
	* @param count 截取长度
	* @return 截取字符串
	*/
	public static String middle(String input, int index, int count) {
	if (input==""||input==null) {
	   return "";
	}
	count = (count > input.length() - index + 1) ? input.length() - index + 1 :
	count;
	return input.substring(index - 1, index + count - 1);
	}
	
	
	/**
	 * 去掉字符串末尾的逗号
	 * @param strs
	 * @return
	 */
	public static String moveCommas(String strs){
		strs=strs.trim();
    	if(strs.endsWith(",")){
    		strs=strs.substring(0,strs.length()-1);
		}
    	return strs;
	}
	
	/**
	 * 去重
	 * @param strs
	 * @return
	 */
	public static String cleanDubStr(String strs){
		strs=strs.trim();
		String[] sarry=strs.split(",");
		Set<String> sset = new HashSet<String>();
		for(int i=0;i<sarry.length;i++){
			if(!"".equals(sarry[i])){
				sset.add(sarry[i]);
			}
		}
		strs="";
		Iterator<String> iterator=sset.iterator();
		while(iterator.hasNext()){
			strs+=iterator.next()+",";
		}
		strs=moveCommas(strs);
		return strs;
	}
	
	/**
	 *  把sring型 id  以，分割 返回
	 * @param strIds
	 * @return
	 */
	public static List<Long> getIdlistByStrIds(String strIds){
		List<Long> idlist = new ArrayList<Long>();
		if(null != strIds && !"".equals(strIds)){
			String[] ids=strIds.split(",");
			for(String s : ids){
				if(null != s && !"".equals(s.trim()) && !",".equals(s.trim())){
					idlist.add(Long.parseLong(s));
				}
			}
		}
		return idlist;
	}
	
	
	/**
	 * @author zyen
	 * @param strIds
	 * @return Long list
	 */
	public static List getIdlistByStrIdv(String strIds){
		List<Long> idlist = new ArrayList<Long>();
		if(null != strIds && !"".equals(strIds)){
			String[] ids=strIds.split(",");
			long it;
			for(String s : ids){
				if(s!=null && !"".equals(s.trim()) && !",".equals(s.trim())){
					it=Long.parseLong(s);
					idlist.add(it);
				}
			}
		}
		return idlist;
	}
	
	/**
	 * @author zyen
	 * @param strIds
	 * @return String list
	 */
	public static List<String> getIdStrlistByStrId(String strIds){
		List<String> idlist = new ArrayList<String>();
		if(null != strIds && !"".equals(strIds)){
			String[] ids=strIds.split(",");
			long it;
			for(String s : ids){
				if(null != s && !"".equals(s.trim()) && !",".equals(s.trim())){
					idlist.add(s);
				}
			}
		}
		return idlist;
	}
	
	/**
	 *  把sring型 ids 以，分割 返回
	 * @param strIds
	 * @return
	 */
	public static String getStrsByids(String ids){
		String  _strs="";
		String[] tary=new String[ids.length()];
		tary=ids.split(",");
		for(int i=0;i<tary.length;i++){
			if(tary[i]!=null&&!"".equals(tary[i].trim())&&!",".equals(tary[i].trim())){
				_strs=_strs+tary[i].trim()+",";
			}
		}
		_strs=_strs.trim();
		if(_strs!=null&&!"".equals(_strs.trim())){
			if(_strs.endsWith(",")){
				_strs=_strs.substring(0,_strs.length()-1);
			}
		}
		return _strs;
	}
	
	/**判断号码所属运营商
	 * @author gxy
	 * @param phone
	 * @return
	 */
	public String getPhoneType(String phone){
		//电信
		String[] temecomArray = {"133","153","180","189"};
		//联通
		String[] unicomArray = {"130","131","132","155","156","185","186"};
		//移动
		String[] moveArray = {"134","135","136","137","138","139","150","151","152","157","158","159","187","188"};
		
		Set<String> telecom = new HashSet<String>(Arrays.asList(temecomArray)); 
		Set<String> unicom = new HashSet<String>(Arrays.asList(unicomArray)); 
		Set<String> move = new HashSet<String>(Arrays.asList(moveArray)); 
		
		//判断是不是手机号码
		if (phone == null || phone.length() != 11){
			return "不是手机号码";
		}else{
			String str = phone.substring(0, 3);
			if (telecom.contains(str)){
				return "电信";
			}else if (unicom.contains(str)){
				return "联通";
			}else if (move.contains(str)){
				return "移动";
			}else {
				return "不是手机号码";
			}
		}		
	}
	
	//过滤文本框输入javascript文本
	 public static String filterForHTMLValue(String _sContent) {
	        if (_sContent == null)
	            return "";

	        // _sContent = replaceStr(_sContent,"</script>","<//script>");
	        // _sContent = replaceStr(_sContent,"</SCRIPT>","<//SCRIPT>");

	        char[] srcBuff = _sContent.toCharArray();
	        int nLen = srcBuff.length;
	        if (nLen == 0)
	            return "";

	        StringBuffer retBuff = new StringBuffer((int) (nLen * 1.8));

	        for (int i = 0; i < nLen; i++) {
	            char cTemp = srcBuff[i];
	            switch (cTemp) {
	            case '&': // 转化：& -->&amp;why: 2002-3-19
	                // caohui@0515
	                // 处理unicode代码
	                if ((i + 1) < nLen) {
	                    cTemp = srcBuff[i + 1];
	                    if (cTemp == '#')
	                        retBuff.append("&");
	                    else
	                        retBuff.append("&amp;");
	                } else
	                    retBuff.append("&amp;");
	                break;
	            case '<': // 转化：< --> &lt;
	                retBuff.append("&lt;");
	                break;
	            case '>': // 转化：> --> &gt;
	                retBuff.append("&gt;");
	                break;
	            case '\"': // 转化：" --> &quot;
	                retBuff.append("&quot;");
	                break;
	            default:
	                retBuff.append(cTemp);
	            }// case
	        }// end for

	        return retBuff.toString();
	    }
	 
	 /**
	 *@Description: 根据bean属性list去重
	 *@param str
	 *@return
	 *@date 2014-3-1 上午10:49:43
	 *@author zyen
	  */
	 public static <T> List<T> removeDuplication(List<T> list, String... keys) {
			if (list == null || list.isEmpty()) {
				System.err.println("List is empty.");
				return list;
			}
			if (keys == null || keys.length < 1) {
				System.err.println("Missing parameters.");
				return list;
			}
			for (String key : keys) {
				if (StringUtils.isBlank(key)) {
					System.err.println("Key is empty.");
					return list;
				}
			}
			List<T> newList = new ArrayList<T>();
			Set<String> keySet = new HashSet<String>();
			for (T t : list) {
				StringBuffer logicKey = new StringBuffer();
				for (String keyField : keys) {
					try {
						logicKey.append(BeanUtils.getProperty(t, keyField));
						logicKey.append(SEPARATOR);
					} catch (Exception e) {
						e.printStackTrace();
						return list;
					}
				}
				if (!keySet.contains(logicKey.toString())) {
					keySet.add(logicKey.toString());
					newList.add(t);
				} else {
					System.err.println(logicKey + " has duplicated.");
				}
			}
			return newList;
		}
		
	 /**
	 *@Description: 字符串判空 
	 *@param str
	 *@return
	 *@date 2014-3-1 上午10:49:43
	 *@author zyen
	  */
		public static boolean isNotEmpty(String str) {
			return (str != null && str.length()!= 0);
		}
}
