package com.sbs.system.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.common.tools.SaltCreaterUtil;
import com.sbs.common.tools.StringUtil;
import com.sbs.system.login.service.PasswordService;
import com.sbs.system.user.entity.User;
import com.sbs.system.user.mapper.UserMapper;
@Service("userservice")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordService passwordService;
	
	@Override
	public User findUserByUserId(Integer userId) {
		
		User user = userMapper.findUserByUserId(userId);
		return user;
	}

	@Override
	public List<User> findUserList(User user) {
		List<User> list =  userMapper.findUserList(user);
		return list;
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectUserByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByLoginName(String loginName) {
		// TODO Auto-generated method stub
		User user = userMapper.findUserByLoginName(loginName);
		return user;
	}

	@Override
	public int deleteUserById(Integer userId) {
		int res = userMapper.deleteUserById(userId);
		return res;
	}

	@Override
	public int batchDeleteUser(int[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("static-access")
	@Override
	public int saveUser(User user) {
		int count = -99;
		if (StringUtil.isNotNull(user)) {
			if (StringUtil.isNotNull(user.getUserId())) {
				count = userMapper.updateUser(user);
			}else {
				String salt = SaltCreaterUtil.createSalt();
				user.setSalt(salt);
				user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), salt));
				count = userMapper.saveUser(user);
			}
		}
		return count;
	}

	@Override
	public int resetUserPassword(User user) {
		// TODO Auto-generated method stub
		return 0;
	}
}
