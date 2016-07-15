package com.omg.evn.util.sysutil;

import java.util.List;

public class GridePage<T> {
	//总数
	private long total;
	//
	private List<T> rows;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
