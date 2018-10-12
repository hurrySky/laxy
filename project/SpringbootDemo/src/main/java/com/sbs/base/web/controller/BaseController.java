package com.sbs.base.web.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sbs.base.web.entity.BaseEntity;
import com.sbs.base.web.pagination.PaginationDomain;
import com.sbs.base.web.pagination.TableDataInfo;
import com.sbs.common.enumeration.ModuleEnum;
import com.sbs.common.tools.PaingationParamBuildUtil;
import com.sbs.common.tools.SearchParamBuildUtil;
import com.sbs.common.tools.ServletRequestUtil;
import com.sbs.common.tools.StringUtil;
import com.sbs.system.menu.entity.Menu;
import com.sbs.system.role.entity.Role;
import com.sbs.system.user.entity.User;

public class BaseController {
	
	/**构建分页条件
	 * 
	 */
	public void buildPagination() {
		 PaginationDomain paginationDomain = PaingationParamBuildUtil.pageParamBuild();
		 Integer pageNum = paginationDomain.getPageNum();
		 Integer pageSize = paginationDomain.getPageSize();
		 String orderBy = paginationDomain.getOrderBy();
		
		 if (pageSize != -1 && pageSize >= 0) {
			 PageHelper.startPage(pageNum, pageSize, orderBy);
		 }
	}
	
	/**
	 *  做分页
	 * @param list
	 * @return 分页数据
	 */
	public TableDataInfo doPagination(List list) {
		PageInfo<?> pageInfo = new PageInfo<Object>(list);
		Integer draw = StringUtil.valueOfInteger(ServletRequestUtil.getRequestParam("draw"));
		TableDataInfo data = new TableDataInfo(list, draw, (int)pageInfo.getTotal(), (int)pageInfo.getTotal());
		return data;
	}
	
	/**
	 * 返回查询条件
	 * @return 查询全局条件值
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Object doSearch(ModuleEnum moduleEnum) {
		BaseEntity entity = null;
		if (ModuleEnum.USER.equals(moduleEnum)) {
			entity = SearchParamBuildUtil.getSearchEntity(new User());
		} else if(ModuleEnum.MENU.equals(moduleEnum)) {
			entity = SearchParamBuildUtil.getSearchEntity(new Menu());
		} else if(ModuleEnum.ROLE.equals(moduleEnum)) {
			entity = SearchParamBuildUtil.getSearchEntity(new Role());
		}
		
		return entity;
	}
}
