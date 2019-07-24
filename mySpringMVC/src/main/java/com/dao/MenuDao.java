package com.dao;

import java.util.List;

import com.entity.Menu;

public interface MenuDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKeyWithBLOBs(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
	 * 根据用户id获取用户菜单
	 * @param userId 用户id
	 * @return
	 */
	List<Menu> getUserMenuList(String userId);
    
    /**
	 * 根据用户id获取用户一级菜单
	 * @param userId 用户id
	 * @return
	 */
	List<Menu> getUserParentMenuList(String userId);
	
	/**
	 * 根据父级菜单id获取子菜单
	 * @param parentId 父菜单id
	 * @return
	 */
	List<Menu> getChildMenuByParentId(String parentId);

	/**
	 * 获取全部一级菜单
	 * @return
	 */
	List<Menu> getParentMenuList();
	
}