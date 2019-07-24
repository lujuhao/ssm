package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Menu;
import com.entity.User;
import com.service.MenuSerivce;

/**
 * 菜单Controller
 * @author ljh
 */
@Controller
@RequestMapping("/menu/")
public class MenuController extends BaseController{
	
	@Autowired
	private MenuSerivce menuSerivce;

	/**
	 * 获取当前用户拥有的菜单权限
	 * @return
	 */
	@RequestMapping("getUserMenuList")
	@ResponseBody
	public List<Menu> getUserMenuList(HttpSession session){
		User user = getCurrentUser();
		return menuSerivce.getUserMenuList(user.getId());
		
	}
	
	/**
	 * 获取全部菜单权限
	 * @return
	 */
	@RequestMapping("getMenuList")
	@ResponseBody
	public List<Menu> getMenuList(){
		return menuSerivce.getMenuList();
		
	}
}
