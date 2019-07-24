package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.UserRoleDao;
import com.entity.Role;
import com.entity.UserRole;
import com.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public int insert(UserRole record) {
		return userRoleDao.insert(record);
	}

	/**
     * 根据用户id获取所属角色
     * @param userId
     * @return
     */
	@Override
	public List<Role> getRoleListByUserId(String userId) {
		return userRoleDao.getRoleListByUserId(userId);
	}
}