package com.list.shaddock.common.vo;

import java.util.List;

public class PageRows<T> {
	
	long current = 0l;
	
	long total = 0l;
	
	List<T> rows = null;

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

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
