package com.omg.xbase.xexp;

import java.io.Serializable;

/**
 * 系统自定义异常
 * @author zyen
 * @version 1.0
 *
 */
public class AppException extends RuntimeException implements Serializable {
	
	private String msg;
	
    private String code;

    
    /**
	 * 输出信息
	 * 
	 * @param msg  输出信息
	 *           
	 */
    public AppException(String msg)
    {
    	this.code = msg;
        this.msg=msg;
    }

    
    /**
	 * 输出异常对象
	 * 
	 * @param ex  输出异常
	 *           
	 */
    public AppException(Throwable ex)
    {
        super(ex);
    }

    
    /**
	 * 输出信息和异常对象
	 * 
	 * @param msg	输出信息
	  * @param ex	可能输出的异常
	 *           
	 */
    
    public AppException(String msg,Throwable ex)
    {
        super(ex);
        this.msg=msg;
    }

    
    
    /**
	 * 输出可能出现异常的代码和信息
	 * @param code	可能出现异常的代码
	  * @param msg	输出信息
	 *           
	 */
    public AppException(String code,String msg)
    {
        this.code=code;
        this.msg=msg;
    }

    
    
    
    public String toString()
    {
        return "异常代码:"+code+"。异常原因:"+msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
    	return this.msg;
    }
}
