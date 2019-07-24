package com.service;

import java.util.List;

import com.entity.Menu;

public interface MenuSerivce {
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
	 * 获取全部菜单权限
	 * @return
	 */
	List<Menu> getMenuList();
}