package com.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.User;
import com.github.pagehelper.PageHelper;
import com.service.UserService;
import com.util.RandomUtil;
import com.util.ShiroMd5Util;
import com.util.SysConfigContants;
import com.vo.Page;

/**
 * User service层接口实现
 * @author ljh
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	/**
	 * 根据用户id删除用户
	 * @param userId 用户id
	 * @param returnResult 返回信息
	 * @return
	 */
	@Override
	public int deleteByID(String id) {
		String [] ids=id.split(",");
		if (ids.length > 0) {
			return userDao.deleteByIDList(Arrays.asList(ids));
		}else {
			return 0;
		}
		
	}

	/**
	 * 添加用户
	 * @param record
	 * @return
	 */
	@Override
	@Transactional
	public int insert(User record) {
		record.setId(RandomUtil.getUUID());
		
		//密码加密
		record.setPassword(ShiroMd5Util.SysMd5(record));
		
		//设置默认头像
		record.setHeadImg(SysConfigContants.USER_HEADIMG_DEFAULT);
		return userDao.insert(record);
	}

	/**
	 * 根据用户id获取用户详情
	 * @param id
	 * @return
	 */
	@Override
	public User getUserById(String id) {
		return userDao.getUserById(id);
	}

	/**
     * 根据用户名称获取用户详情
     * @param loginName 用户名称
     * @return
     */
	@Override
	public User getUserByName(String loginName) {
		return userDao.getUserByName(loginName);
	}
	
	@Override
	public int update(User record) {
		return userDao.update(record);
	}

	@Override
	public List<User> selectUserList(User user) {
		return userDao.selectUserList(user);
	}

	/**
     * 分页查询用户列表
     * @param page 分页信息
     * @param user 过滤条件
     * @return
     */
	@Override
	public Page<User> selectUserByPage(Page<User> page,User user) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		user.setPage(page);
		List<User> list = userDao.selectUserList(user);
		page.setList(list);
		return page;
	}

}