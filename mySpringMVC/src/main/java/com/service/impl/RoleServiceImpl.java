package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dao.RoleDao;
import com.dao.RoleMenuDao;
import com.entity.Role;
import com.entity.User;
import com.github.pagehelper.PageHelper;
import com.service.RoleService;
import com.util.RandomUtil;
import com.vo.Page;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleMenuDao roleMenuDao;

	/**
	 * 添加角色
	 * @param 角色
	 * @return 角色id
	 */
	@Override
	public String insert(Role record) {
		String id = RandomUtil.getUUID();
		record.setId(id);
		roleDao.insert(record);
		return id;
	}

	/**
	 * 根据角色id删除角色与角色权限
	 * @param 角色id
	 */
	@Override
	public int deleteById(String id) {
		if (!StringUtils.isEmpty(id)) {
			String [] idArray = id.split(",");
			for (int i = 0; i < idArray.length; i++) {
				roleMenuDao.deleteByRoleId(idArray[i]);
				roleDao.deleteById(idArray[i]);
			}
			return idArray.length;
		}else {
			return 0;
		}
	}

	@Override
	public int update(Role record) {
		return roleDao.update(record);
	}

	@Override
	public Role getRoleById(String id) {
		return roleDao.getRoleById(id);
	}

	/**
	 * 分页查询角色列表
	 * @param role 过滤条件
     * @param pageNo 页码
     * @param pageSize 页宽
	 * @return
	 */
	@Override
	public Page<User> selectRoleByPage(Page<User> page, Role role) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		role.setPage(page);
		List<User> list = roleDao.selectRoleList(role);
		page.setList(list);
		return page;
	}
	
	


}