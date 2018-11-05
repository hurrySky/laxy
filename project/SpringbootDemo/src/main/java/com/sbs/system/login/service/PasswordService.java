package com.sbs.system.login.service;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sbs.common.exception.user.UserPasswordNotMatchException;
import com.sbs.common.exception.user.UserPasswordRetryLimitExceedException;
import com.sbs.system.user.entity.User;

@Service
public class PasswordService {

	@Autowired
    private CacheManager cacheManager;
	
	private Cache<String, Object> loginRecordCache;
	
	@Value(value = "${shiro.user.password.maxRetryCount}")
	private int maxRetryCount;
//	@PostConstruct
//	public void init() {
//		loginRecordCache = cacheManager.getCache("loginRecordCache");
//	}
	
	public void validate(User user, String password) {
		
	}
	
	/**
	 * 校验用户是否密码是否正确
	 * @param user
	 * @param newPassword
	 * @return
	 */
	public boolean checkUser(User user, String newPassword) {
		loginRecordCache = cacheManager.getCache("loginCountCache");
		AtomicInteger retryCount = (AtomicInteger) loginRecordCache.get(user.getLoginName());
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			loginRecordCache.put(user.getLoginName(), retryCount);
		}
		if (retryCount.incrementAndGet() > maxRetryCount) {
			System.out.println("账户已被锁定10分钟！");
			throw new UserPasswordRetryLimitExceedException();
		}
		
		String password = user.getPassword().trim();
		//System.out.println(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
		Boolean flag =  password.equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
		if (!flag) {
			loginRecordCache.put(user.getLoginName(), retryCount);
			throw new UserPasswordNotMatchException();
		}
		return flag;
	}
	
	/**
	 * 返回加盐加密后的 密码
	 * @param username
	 * @param password
	 * @param salt
	 * @return
	 */
	 public static String encryptPassword(String loginName, String password, String salt) {
		 String encryPassword = new Md5Hash(loginName + password + salt).toHex().toString();
		 return encryPassword;
	 }
	 public static void main(String [] args){
		 System.out.println(encryptPassword("luoji", "luoji", "wecfg"));
	 }
}
