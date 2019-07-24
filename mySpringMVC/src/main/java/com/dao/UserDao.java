package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.User;

/**
 * User dao层接口
 * @author ljh
 */
public interface UserDao {
    int deleteByID(String id);
    
    int deleteByIDList(@Param("idList")List<String> idList);

    /**
	 * 添加用户
	 * @param record
	 * @return
	 */
    int insert(User user);

    /**
	 * 根据用户id获取用户详情
	 * @param id
	 * @return
	 */
    User getUserById(String id);

    /**
     * 根据用户名称获取用户详情
     * @param loginName 用户名称
     * @return
     */
	User getUserByName(String loginName);
    
    int update(User record);
    
    List<User> selectUserList(User user);

    

}