package com.list.shaddock.common.vo;

import java.io.Serializable;
import java.util.Map;

public class FormData<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1662086656491622156L;
	
	private T bo = null;
	
	private Map<Object,Object> other = null;
	
	private String sort = null;
	
	private String order = null;
	
	private long page = 0l;
	
	private long rows = 0l;

	public T getBo() {
		return bo;
	}

	public void setBo(T bo) {
		this.bo = bo;
	}

	public Map<Object, Object> getOther() {
		return other;
	}

	public void setOther(Map<Object, Object> other) {
		this.other = other;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}
	
}
