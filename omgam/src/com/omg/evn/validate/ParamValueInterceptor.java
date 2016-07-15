package com.omg.evn.validate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


/**
 * Created by IntelliJ IDEA.
 * User: 
 * Date: 12-5-16
 * Time: 下午4:16
 * 请求参数拦截器
 */
public class ParamValueInterceptor extends AbstractInterceptor {


    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        //获取request域中信息
        HttpServletRequest req = ServletActionContext.getRequest();
        //获得请求类型
        String header = req.getHeader("X-Requested-With");
        Map parameters =invocation.getInvocationContext().getParameters();
        //下面开始遍历组装
        Iterator iterator = parameters.entrySet().iterator();
        Map<String,Object> paramValueMap =new HashMap<String,Object>();
         while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                String[] oldValues =null; //数组
                String   oldValue=null;   //字符串
               if (entry.getValue() instanceof String[]){
                   oldValues= ((String[]) entry.getValue());
               }else if (entry.getValue() instanceof String){
                   oldValue=entry.getValue().toString();
                }else{
                   oldValue=entry.getValue().toString();
                }

                if(oldValues.length>0){    //判断是否数组
                    String[] tempAarry=null;
                    String tempStr="";
                    for (int i = 0; i < oldValues.length; i++) {
                            if(!"".equals(oldValues[i].toString())){
                                tempStr+=returnParamValue(oldValues[i].toString(),header)+",";
                            }
                    }
                     if(!"".equals(tempStr)){
                         tempAarry=tempStr.split(",");
                     }
                    paramValueMap.put(key,tempAarry); //处理字符串转数据问题
                   // paramValueMap.put(key,oldValues);
                }else if(!"".equals(oldValue)){
                    paramValueMap.put(key,returnParamValue(oldValues[0].toString(),header));
                }else{
                    paramValueMap.put(key,null);
                }
        }
        invocation.getInvocationContext().setParameters(paramValueMap);
        return invocation.invoke();
  }

     //返回请求参数值
    private String returnParamValue(String param ,String reqType){
       if(!"".equals(param)&&param!=null){
           if (reqType != null && "XMLHttpRequest".equals(reqType)){   //ajax请求
               param=ParamValiDateUtils.StringSqlFilter(param);
           }else{    //普通请求
               param=ParamValiDateUtils.StringSqlFilter(param);
           }
       }
        return param;
    }



   /*
      while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                String[] oldValues =null; //数组
                String   oldValue=null;   //字符串
               if (entry.getValue() instanceof String[]){
                   //oldValues=new String[]{entry.getValue().toString()};
                   oldValues= ((String[]) entry.getValue());
               }else if (entry.getValue() instanceof String){
                    //oldValues=new String[]{entry.getValue().toString()};
                   oldValue=entry.getValue().toString();
                }else{
                    //oldValues=(String[]) entry.getValue();
                   oldValue=entry.getValue().toString();
                }
                List  newValuesList = new ArrayList();
                if(oldValues.length>0){
                    for (int i = 0; i < oldValues.length; i++) {
                        newValuesList.add(returnParamValue(oldValues[i].toString(),header));//字符转义处理);
                    }
                    paramValueMap.put(key,newValuesList.toArray());  //转换成数组
                }else if(!"".equals(oldValue)){
                    //newValueStr =returnParamValue(oldValues[0].toString(),header);//字符转义处理
                    paramValueMap.put(key,returnParamValue(oldValues[0].toString(),header));
                }else{
                    paramValueMap.put(key,null);
                }

               /* if (oldValues.length > 1) {
                        newValueStr = "{";
                        for (int i = 0; i < oldValues.length; i++) {
                            newValueStr +=returnParamValue(oldValues[i].toString(),header);//字符转义处理
                            if (i != oldValues.length - 1) {
                                newValueStr += ",";
                            }
                        }
                        newValueStr += "}";
                    } else if (oldValues.length == 1) {
                        newValueStr =returnParamValue(oldValues[0].toString(),header);//字符转义处理
                    } else {
                        newValueStr = "null";
                    }*/
    // paramValueMap.put(key,newValueStr);

}