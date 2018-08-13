package com.sbs.common.tools;

import java.util.Calendar;
import java.util.Random;

import com.sbs.common.ModuleEnum;

public class CodeCreateUtil {

	public static void main(String[] args) {
		System.out.println(createCode("USER"));
	}

	public static String createCode(String module) {
		String code = "";
		Calendar time=Calendar.getInstance(); 
		code =  time.get(Calendar.YEAR) + "" + (time.get(Calendar.MONTH) + 1) + time.get(Calendar.DATE) + time.get(Calendar.MINUTE) + "_";
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
