package com.scht.front.bean;

public class RetData {
	
	private Integer pageNo;
	private Integer pageSize;
	private Integer total;
	private Object data;
	
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public RetData(Integer pageNo,Integer pageSize,Object data,Integer total){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.data = data;
		this.total = total;
	}
	
	public RetData(Object data){
		this.data = data;
	}
	
}
