package com.sbs.common.tools;

import com.sbs.base.web.pagination.PaginationDomain;

/**
 * 分页参数构建工具类
 * @author Your Clever
 *
 */
public class PaingationParamBuildUtil {
	/**
	 * 分页参数构建
	 * @return PaginationDomain
	 */
	public static PaginationDomain pageParamBuild() {
		
		PaginationDomain paginationDomain = new PaginationDomain();
		
		Integer draw = StringUtil.valueOfInteger(ServletRequestUtil.getRequestParam("draw"));
		
		Integer start = StringUtil.valueOfInteger(ServletRequestUtil.getRequestParam("start"));
		Integer length = StringUtil.valueOfInteger(ServletRequestUtil.getRequestParam("length"));
		
		// 第几列需要排序
		Integer columnsNumber =  StringUtil.valueOfInteger(ServletRequestUtil.getRequestParam("order[0][column]"));
		// 排序方式，升序/倒叙
		String orderType =  (String) ServletRequestUtil.getRequestParam("order[0][dir]");
		// 排序列
		String orderColumn =  StringUtil.fomatterColumn((String) ServletRequestUtil.getRequestParam("columns[" + columnsNumber +"][data]"));
		
		int startPage = start / length + 1;
		paginationDomain.setPageNum(startPage);
		paginationDomain.setPageSize(length);
		paginationDomain.setDraw(draw);
		paginationDomain.setOrderColumn(orderColumn);
		paginationDomain.setOrderType(orderType);
		paginationDomain.setOrderBy(orderColumn + " " + orderType);
		return paginationDomain;
	}
}
