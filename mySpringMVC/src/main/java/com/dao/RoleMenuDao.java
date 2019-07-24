package com.dao;

import java.util.List;

import com.entity.Menu;
import com.entity.RoleMenu;

public interface RoleMenuDao {
	int insert(RoleMenu roleMenu);
	
	/**
	 * 根据角色id获取菜单
	 * @param roleId
	 * @return
	 */
	List<Menu> getMenuListByRoleId(String roleId);
	
	/**
	 * 根据用户id获取菜单
	 * @param userId
	 * @return
	 */
	List<Menu> getMenuListByUserId(String userId);
	
	/**
	 * 根据角色id删除角色权限
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(String roleId);
}
