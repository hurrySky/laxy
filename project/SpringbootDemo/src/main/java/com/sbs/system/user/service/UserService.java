package com.sbs.system.user.service;

import java.util.List;

import com.sbs.system.user.entity.User;

public interface UserService {
	
	/**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<User> findUserList(User user);
	
	/**
	 * 根据用户id 查找用户
	 * @param id
	 * @return 用户对象信息
	 */
	public User findUserByID(int id);
	
	/**
	 * 根据邮箱地址查找用户
	 * @param id
	 * @return 用户对象信息
	 */
	public User findUserByEmail(String email);
	
	/**
     * 通过手机号码查询用户
     * 
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    public User selectUserByPhoneNumber(String phoneNumber);
    
    /**
     * 通过登录名查询用户
     * 
     * @param loginName 登录名
     * @return 用户对象信息
     */
    public User findUserByLoginName(String loginName);
    
    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(int id);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteUser(int[] ids);

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int saveUser(User user);

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 修改用户密码信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int resetUserPassword(User user);
}
