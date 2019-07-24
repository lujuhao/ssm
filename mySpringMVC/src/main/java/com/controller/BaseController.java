package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.User;
import com.vo.ReturnResult;

@Controller
public class BaseController {

	/**
	 * 初始化请求返回值
	 * @param returnResult
	 * @return
	 */
	@ModelAttribute
	public ReturnResult initReturnResult(@RequestParam(required = false) ReturnResult returnResult) {
		if (null == returnResult) {
			returnResult = new ReturnResult();
		}
		returnResult.setCode(1);//请求返回layer图标
		returnResult.setStatus(HttpStatus.OK.value());//请求返回状态
		returnResult.setMsg("请求成功");//请求返回信息
		return returnResult;
	}
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public User getCurrentUser(){
		Subject subject = SecurityUtils.getSubject();
		User user =(User)subject.getPrincipal();
		return user;
	}
	
}
