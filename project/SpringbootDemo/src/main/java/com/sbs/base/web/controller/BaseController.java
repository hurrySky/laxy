package com.sbs.base.web.controller;

import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sbs.base.web.pagination.PaginationDomain;
import com.sbs.base.web.pagination.TableDataInfo;
import com.sbs.common.tools.PaingationParamBuildUtil;
import com.sbs.common.tools.ServletRequestUtil;
import com.sbs.common.tools.StringUtil;

public class BaseController {
	
	public void buildPagination() {
		 PaginationDomain paginationDomain = PaingationParamBuildUtil.pageParamBuild();
		 Integer pageNum = paginationDomain.getPageNum();
		 Integer pageSize = paginationDomain.getPageSize();
		 String orderBy = paginationDomain.getOrderBy();
		
		 if (pageSize != -1 && pageSize >= 0) {
			 PageHelper.startPage(pageNum, pageSize, orderBy);
		}
	}
	
	public TableDataInfo doPagination(List list) {
		PageInfo<?> pageInfo = new PageInfo<Object>(list);
		Integer draw = StringUtil.valueOfInteger(ServletRequestUtil.getRequestParam("draw"));
		TableDataInfo data = new TableDataInfo(list, draw, (int)pageInfo.getTotal(), (int)pageInfo.getTotal());
		return data;
	}
}
