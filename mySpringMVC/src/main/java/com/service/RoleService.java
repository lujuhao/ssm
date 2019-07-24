package com.service;

import com.entity.Role;
import com.entity.User;
import com.vo.Page;

public interface RoleService {
	
	/**
	 * 添加角色
	 * @param 角色
	 * @return 角色id
	 */
	String insert(Role record);

    int deleteById(String id);
    
    int update(Role record);
    
    Role getRoleById(String id);

    /**
	 * 分页查询角色列表
	 * @param role 过滤条件
     * @param pageNo 页码
     * @param pageSize 页宽
	 * @return
	 */
	Page<User> selectRoleByPage(Page<User> page, Role role);
}