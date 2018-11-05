package com.sbs.common.tools;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import com.sbs.common.enumeration.ModuleEnum;

public class SaltCreaterUtil {

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString());
		System.out.println(String.valueOf(UUID.randomUUID()));
		System.out.println(createSalt());
	}

	/**
	 * 生成盐 
	 * @param 
	 * @return
	 */
	public static String createSalt() {
		String salt = String.valueOf(UUID.randomUUID());
		return salt.substring(0, 5);
	}
}
