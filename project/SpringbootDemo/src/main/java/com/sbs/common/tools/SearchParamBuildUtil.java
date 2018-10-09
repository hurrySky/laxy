package com.sbs.common.tools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  构建全局查询参数
 * @author lixin
 */
public class SearchParamBuildUtil{
	
	/**
	 * 查询参数构建
	 * @return Map集合，其中包含 查询列的set集合和全局查询值
	 */
	private static Map<String, Object> searchParamBuild() {
		// 获得全局查询值
		String searchValue =  (String) ServletRequestUtil.getRequestParam("search[value]");

		// 构建 查询 列  及查询值 map
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashSet<String> set = new HashSet<String>();
		set.add("searchValue");
		AtomicInteger atomicInteger = new AtomicInteger(0);
		Object[] object = (Object[]) ServletRequestUtil.getRequestArrParam("columns[" + atomicInteger +"][searchable]");
		//System.out.println((String)object[0]);
		Boolean flag = Boolean.valueOf((String)object[0]);
		if (StringUtil.isNotNull(object)) {
			if (flag) {
				String searchColumn = (String)ServletRequestUtil.getRequestParam("columns[" + atomicInteger +"][data]");
				if (StringUtil.isNotNull(searchColumn)) {
					//map.put(searchColumn, "1");
					set.add(searchColumn);
				}
			}
			
			Boolean flag2 = null;
			while (StringUtil.isNotNull(object)) {
				flag2 = false;
				atomicInteger.incrementAndGet();
				object = (Object[]) ServletRequestUtil.getRequestArrParam("columns[" + atomicInteger +"][searchable]");
				if (StringUtil.isNotNull(object)) {
					if (object.length > 0) {
						flag2 = Boolean.valueOf((String)object[0]);
					}
				}
				
				if (flag2) {
					String searchColumn2 = (String)ServletRequestUtil.getRequestParam("columns[" + atomicInteger +"][data]");
					if (StringUtil.isNotNull(searchColumn2)) {
						//map.put(searchColumn2, "1");
						set.add(searchColumn2);
					}
				}
			}
		}
		map.put("searchColumn", set);
		map.put("searchValue", searchValue);
		return map;
	}
	
	public static<T> T getSearchEntity(T t) {
		Map<String, Object> map = SearchParamBuildUtil.searchParamBuild();
		Set<String> set = (Set<String>) map.get("searchColumn");
		String searchValue = (String) map.get("searchValue");
		Field[] currentField = t.getClass().getDeclaredFields();
		Field[] superField = t.getClass().getSuperclass().getDeclaredFields();
		Class SearchEntity = null;
		try {
			SearchEntity = Class.forName(t.getClass().getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			t = (T) SearchEntity.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		Method method = null;
		for (String str : set) {
			// 遍历当前类属性
			for (int i = 0; i < currentField.length; i++) {
				//System.out.println(currentField[i].toString());
				if (currentField[i].toString().indexOf(str) > -1) {
					// 获得属性的set方法
					try {
						method = SearchEntity.getDeclaredMethod("set" + String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1, str.length()),String.class);
					} catch (NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
					 // 执行属性的 set方法
					try {
						method.invoke(t, "1");
					} catch (IllegalAccessException
							| IllegalArgumentException
							| InvocationTargetException e) {
							e.printStackTrace();
					}
				}
			}
			// 遍历父类类属性
			for (int i = 0; i < superField.length; i++) {
				if (superField[i].toString().indexOf(str) > -1) {
					// 获得属性父类set方法
					try {
						method = SearchEntity.getSuperclass().getDeclaredMethod("set" + String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1, str.length()),String.class);
					} catch (NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
					 // 执行 set方法
					try {
						if ("searchValue".equals(str)) {
							method.invoke(t, searchValue);
						}else {
							method.invoke(t, "1");
						}
					} catch (IllegalAccessException
							| IllegalArgumentException
							| InvocationTargetException e) {
							e.printStackTrace();
					}
				}
			}
		}
		return t;
	}
	
	/**
	 * 
	 */
//	private void getAndExecuteMethod(Class SearchEntity, Method method, Field[] field, String str) {
//		for (int i = 0; i < field.length; i++) {
//			System.out.println(field[i].toString());
//			if (field[i].toString().indexOf(str) > -1) {
//				// 获得属性set方法
//				try {
//					method = SearchEntity.getDeclaredMethod("set" + String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1, str.length()),String.class);
//				} catch (NoSuchMethodException | SecurityException e) {
//					e.printStackTrace();
//				}
//				
//				 // 执行 set方法
//				try {
//					method.invoke(t, "1");
//				} catch (IllegalAccessException
//						| IllegalArgumentException
//						| InvocationTargetException e) {
//						e.printStackTrace();
//				}
//			}
//		}
//	}
}
