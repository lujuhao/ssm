package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.MenuDao;
import com.entity.Menu;
import com.service.MenuSerivce;

@Service
public class MenuSerivceImpl implements MenuSerivce{
	
	@Autowired
	private MenuDao menuDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return menuDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Menu record) {
		return menuDao.insert(record);
	}

	@Override
	public int insertSelective(Menu record) {
		return menuDao.insertSelective(record);
	}

	@Override
	public Menu selectByPrimaryKey(Integer id) {
		return menuDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Menu record) {
		return menuDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Menu record) {
		return menuDao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Menu record) {
		return menuDao.updateByPrimaryKey(record);
	}

	/**
	 * 根据用户id获取用户菜单
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public List<Menu> getUserMenuList(String userId) {
		List<Menu> parentMenuList=menuDao.getUserParentMenuList(userId);
		if (parentMenuList != null) {
			for (Menu menu : parentMenuList) {
				List<Menu> childMenuList=menuDao.getChildMenuByParentId(menu.getId());
				if (childMenuList != null) {
					menu.setChildMenuList(childMenuList);
				}
			}
		}
		
		return parentMenuList;
	}

	/**
	 * 获取全部菜单权限
	 * @return
	 */
	@Override
	public List<Menu> getMenuList() {
		List<Menu> parentMenuList=menuDao.getParentMenuList();
		if (null != parentMenuList && parentMenuList.size() > 0) {
			for (Menu menu : parentMenuList) {
				List<Menu> childMenuList=menuDao.getChildMenuByParentId(menu.getId());
				addChildList(childMenuList,menu);
			}
		}
		
		return parentMenuList;
	}
	
	
	private List<Menu> addChildList(List<Menu> menuList,Menu parentMenu){
		if (null != menuList && menuList.size() > 0) {
			for (Menu menu : menuList) {
				parentMenu.setChildMenuList(menuList);
				List<Menu> childMenuList=menuDao.getChildMenuByParentId(menu.getId());
				while (childMenuList != null && childMenuList.size() > 0) {
					childMenuList = addChildList(childMenuList,menu);
				}
			}
		}
		
		return null;
	}
}