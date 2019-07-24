package com.dao;

import java.util.List;

import com.entity.Role;
import com.entity.UserRole;

public interface UserRoleDao {
    int insert(UserRole record);

    /**
     * 根据用户id获取所属角色
     * @param userId
     * @return
     */
    List<Role> getRoleListByUserId(String userId);
}