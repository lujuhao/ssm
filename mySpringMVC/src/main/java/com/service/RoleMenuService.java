package com.service;

import java.util.List;

import com.entity.Menu;
import com.entity.RoleMenu;

public interface RoleMenuService {
	
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
	 * 添加角色菜单
	 * @param roleId 角色id
	 * @param menuId 菜单id
	 * @return
	 */
	int insertRoleMenu(String roleId,String menuId);
	
	/**
	 * 根据角色id删除角色权限
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(String roleId);
	
	/**
	 * 更新角色菜单
	 * @param roleId 角色id
	 * @param menuId 菜单id
	 * @return
	 */
	int updateRoleMenu(String roleId,String menuId);
}
