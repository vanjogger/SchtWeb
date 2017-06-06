package com.scht.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：unisk_app_jointly_operation   
 * 类名称：PageInfo   
 * 类描述：分页信息
 * 创建人：KeBing 
 * 创建时间：2014-9-22 下午4:21:01     
 * @version 1.0
 */
public class PageInfo<T> implements Serializable{

	private static final long serialVersionUID = 3444007526867205523L;
	
	private Integer limit;				//每页数据数量限制
	private Integer pageIndex;			//当前查询的页码，从0开始计数
	private Integer start;				//开始记录，从0开始计数
	private Integer total;				//根据查询参数查询出的所有记录总数
	private Map<String, Object> params;	//查询参数
	private List<?> result;		//查询结果集

	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		params.put("limit", this.limit);
		params.put("start", this.start);
		this.params = params;
	}
	public List<?> getResult() {
		return result;
	}
	public void setResult(List<?> result) {
		this.result = result;
	}
}
