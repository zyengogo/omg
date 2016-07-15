package com.omg.xbase.xlog;

/**
 * 判断能否正常工作的接口,实现这个接口的类,需要实现canWork方法 这个方法主要就是用来判断当前的这个实例能否工作的.
 * 
 * @author SUN
 * @Date 2010-1-19 下午01:44:53
 * @version 1.0
 * 
 */
public interface ICheckWork {

	/**
	 * 判断是否能正常工作
	 * 
	 * @return 如果能 返回true,不能 返回false
	 */
	public boolean canWork();

}
