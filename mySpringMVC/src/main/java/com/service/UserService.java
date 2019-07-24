package com.service;

import java.util.List;

import com.entity.User;
import com.vo.Page;

/**
 * User service层接口
 * @author ljh
 */
public interface UserService {
	
	/**
	 * 根据用户id删除用户
	 * @param userId 用户id
	 * @param returnResult 返回信息
	 * @return
	 */
	int deleteByID(String id);

	/**
	 * 添加用户
	 * @param record
	 * @return
	 */
    int insert(User user);

    /**
	 * 根据用户id获取用户详情
	 * @param id 用户id
	 * @return
	 */
    User getUserById(String id);

    /**
     * 根据用户名称获取用户详情
     * @param loginName 用户登录名称
     * @return
     */
    User getUserByName(String loginName);
    
    int update(User record);
    
    List<User> selectUserList(User user);
    
    /**
     * 分页查询用户列表
     * @param page 分页信息
     * @param user 过滤条件
     * @return
     */
    Page<User> selectUserByPage(Page<User> page,User user);
}