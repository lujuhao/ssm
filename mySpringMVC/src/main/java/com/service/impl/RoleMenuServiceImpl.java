package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dao.RoleMenuDao;
import com.entity.Menu;
import com.entity.RoleMenu;
import com.service.RoleMenuService;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
	
	@Autowired
	private RoleMenuDao roleMenuDao;

	@Override
	public int insert(RoleMenu roleMenu) {
		return roleMenuDao.insert(roleMenu);
	}

	/**
	 * 根据角色id获取菜单
	 * @param roleId
	 * @return
	 */
	@Override
	public List<Menu> getMenuListByRoleId(String roleId) {
		return roleMenuDao.getMenuListByRoleId(roleId);
	}

	/**
	 * 根据用户id获取菜单
	 * @param userId
	 * @return
	 */
	@Override
	public List<Menu> getMenuListByUserId(String userId) {
		return roleMenuDao.getMenuListByUserId(userId);
	}

	/**
	 * 添加角色菜单
	 * @param roleId 角色id
	 * @param menuId 菜单id
	 * @return
	 */
	@Transactional
	@Override
	public int insertRoleMenu(String roleId, String menuId) {
		if (!StringUtils.isEmpty(menuId)) {
			String [] menuIds = menuId.split(",");
			for (String id : menuIds) {
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuId(id);
				roleMenuDao.insert(roleMenu);
			}
			return menuIds.length;
		}else {
			return 0;
		}
	}

	/**
	 * 根据角色id删除角色权限
	 * @param roleId
	 * @return
	 */
	@Override
	public int deleteByRoleId(String roleId) {
		return roleMenuDao.deleteByRoleId(roleId);
	}

	/**
	 * 更新角色菜单
	 * @param roleId 角色id
	 * @param menuId 菜单id
	 * @return
	 */
	@Override
	public int updateRoleMenu(String roleId, String menuId) {
		//先删除角色原有的权限
		deleteByRoleId(roleId);
		
		if (!StringUtils.isEmpty(menuId)) {
			//重新添加
			String [] menuIds = menuId.split(",");
			for (String id : menuIds) {
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuId(id);
				roleMenuDao.insert(roleMenu);
			}
			return menuIds.length;
		}else {
			return 0;
		}
	}

}
