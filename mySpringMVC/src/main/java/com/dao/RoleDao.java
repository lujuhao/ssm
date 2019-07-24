package com.dao;

import java.util.List;

import com.entity.Role;
import com.entity.User;

public interface RoleDao {
    
    int insert(Role record);

    int deleteById(String id);
    
    int update(Role record);
    
    Role getRoleById(String id);

    /**
     * 查询角色列表
     * @param role
     * @return
     */
	List<User> selectRoleList(Role role);

}