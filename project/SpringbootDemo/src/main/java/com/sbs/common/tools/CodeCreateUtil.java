package com.sbs.common.tools;

import java.util.Calendar;
import java.util.Random;

import com.sbs.common.enumeration.ModuleEnum;

public class CodeCreateUtil {

	public static void main(String[] args) {
		System.out.println(createCode("USER"));
	}

	public static String createCode(String module) {
		String code = "";
		Calendar time=Calendar.getInstance(); 
		String month = ((time.get(Calendar.MONTH) + 1)+"").length() > 1 ? ((time.get(Calendar.MONTH) + 1) + ""):("0" +(time.get(Calendar.MONTH) + 1));
		String day = (time.get(Calendar.DATE)+"").length() > 1 ? (time.get(Calendar.DATE)+"") : ("0" + time.get(Calendar.DATE));
		String minit = (time.get(Calendar.MINUTE)+"").length() > 1 ? (time.get(Calendar.MINUTE)+"") : ("0" + time.get(Calendar.MINUTE));
		code =  time.get(Calendar.YEAR) + "" + month  + day + minit + "_";
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			code = code + r.nextInt(10);
		}
		
		if (StringUtil.isNotNull(module)) {
			if (ModuleEnum.USER.toString().equals(module)) {
				code = "U" + code;
			}
		}
		return code;
	}
}
