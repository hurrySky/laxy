package com.sbs.common.tools;

public class StringUtil {

	/**
     * * 判断一个对象是否为空
     * 
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }
	
	/**
     * 判断一个对象数组是否为空
     * 
     * @param objects 要判断的对象数组
     ** @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }
    
    /**
     * 判断一个对象数组是否为 非空
     * @param object
     * @return true:不为空false:为空
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }
    /**
     * 判断是否数组
     * @param object
     * @return
     */
    public boolean isArray(Object[] object) {
    	return isNotNull(object) && object.getClass().isArray();
    }
    
    /**
     * 去空格
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }
    
    /**
     * 
     * @param args
     */
    public static Integer valueOfInteger(Object object) {
    	if (object instanceof Integer) {
			return (Integer)object;
		} else if (object instanceof Number) {
			return (Integer)((Number) object).intValue();
		} else if (object instanceof String) {
			if ("NaN".equals(object)) {
				return null;
			}else if(isNotNull((String)object)) {
				return Integer.valueOf((String)object);
			}else{
				return null;
			}
		} else if (object instanceof Boolean){
			return (boolean)object == true ? 1:0;
		}else{
			return null;
		}
    }
    
    /**
     *  以大写字母为标识，在该字母前构造 _ ,转大写字母为小写
     * @param str 待转换字符串
     */
    public static String fomatterColumn(String str) {
    	String result = "";
    	for (int i = 0; i < str.length(); i++) {
    		int charInt = (int)str.charAt(i);
			if (charInt > 64 && charInt <91) {
				result = result + "_" + String.valueOf(str.charAt(i)).toLowerCase();
			}else{
				result = result + String.valueOf(str.charAt(i));
			}
		}
    //	System.out.println(result);
    	return result;
    }
    
    /**
     * 
     * @param str 待转换字符串
     * @param spilt 分隔符
     * @return
     */
    public static Integer[] valueOfIntegerArrey(String str, String spilt) {
		if (str != null && !"".equals(str)) {
			String[] strArr = str.split(spilt);
			Integer[] integerArr = new Integer[strArr.length];
	    	for (int i = 0; i < strArr.length; i++) {
	    		integerArr[i] = Integer.valueOf(strArr[i]);
			}
	    	return integerArr;
		} else {
			return null;
		}
    }
    
	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		System.out.println(StringUtil.valueOfInteger(2));
//	//	System.out.println(StringUtil.valueOfInteger("abc"));
//		System.out.println(StringUtil.valueOfInteger(true));
//		System.out.println(StringUtil.valueOfInteger("NaN"));
//		System.out.println(StringUtil.valueOfInteger(1000000000));
//		
//		System.out.println(StringUtil.valueOfInteger(null));
		
		
//		fomatterColumn("aaaaaaa");
//		fomatterColumn("BBBBB");
//		fomatterColumn("AAAbbbb");
//		fomatterColumn("bbbAAAA");
//		fomatterColumn("bbb_AAAA");
		
		valueOfIntegerArrey("4,3,2,5,5,5,5,5", ",");
	}

}
