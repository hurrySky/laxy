package com.sbs.base.web.pagination;

import java.util.List;

public class TableDataInfo {

	/**
	 * 总记录数
	 */
	private Integer recordsTotal;
	private Integer recordsFiltered;
	private Integer draw;
	private List<?> data;
	
	public TableDataInfo() {
		
	}
	/**
	 * 
	 * @param list 
	 * @param draw
	 * @param recordsTotal 记录总数
	 * @param recordsFiltered 过滤后的记录数
	 */
	public TableDataInfo(List<?> list,Integer draw,Integer recordsTotal,Integer recordsFiltered) {
		this.data = list;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.draw = draw;
	}
	
	public Integer getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
}
