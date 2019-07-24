package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.User;
import com.util.Constant;
import com.util.ImageCode;
import com.vo.ReturnResult;

/**
 * 登录Controller
 * @author ljh
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController{
	
	/**
	 * 进入登录页面
	 * @return
	 */
	@RequestMapping("login")
	public String login(HttpSession session){
		User user = getCurrentUser();
		if (null != user) {
			return "index/index";
		}
		return "../login";
	}
	
	/**
	 * 进入系统首页
	 * @return
	 */
	@RequestMapping("index")
	public String index(){
		return "index/index";
	}
	
	/**
	 * 生成图片验证码
	 * @return
	 */
	@RequestMapping("createImgCode")
	public void createImgCode(HttpServletResponse response,HttpSession session){
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("expires", -1);

		ImageCode vCode = new ImageCode(100, 34, 4, 10);
		session.setAttribute("randomCode", vCode.getCode());
		try {
			vCode.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 进入系统仪表盘页面
	 * @return
	 */
	@RequestMapping("dashBoard")
	public String dashBoard(){
		return "index/dashBoard";
	}
	
	/**
	 * 验证用户登录
	 * @param loginName 用户名
	 * @param password 密码
	 * @param rememberMe 是否记住密码
	 * @return
	 */
	@RequestMapping("auth")
	@ResponseBody
	public ReturnResult auth(String loginName,String password,HttpSession session,HttpServletRequest request,
			ReturnResult result,@RequestParam(required=false,defaultValue="false")boolean rememberMe){
		
		UsernamePasswordToken token = new UsernamePasswordToken(loginName, password, rememberMe);
		Subject subject = SecurityUtils.getSubject();
		
		if (subject.isAuthenticated()) {
			result.setStatus(HttpStatus.OK.value());
			result.setMsg("index");
		}
		
		try{
			verifyCode(session,request);
			subject.login(token);  
			session.removeAttribute(Constant.RANDOMCODE);
			result.setStatus(HttpStatus.OK.value());
			result.setMsg("index");
		}catch (UnknownAccountException e) {
			// 用户名未知...
			result.setStatus(HttpStatus.UNAUTHORIZED.value());
			result.setMsg("此用户不存在");
		} catch (IncorrectCredentialsException e) {
			// 凭据不正确，例如密码不正确 ...
			result.setStatus(HttpStatus.UNAUTHORIZED.value());
			result.setMsg("密码不正确");
		} catch (LockedAccountException e) {
			// 用户被锁定，例如管理员把某个用户禁用...
			result.setStatus(HttpStatus.UNAUTHORIZED.value());
			result.setMsg("此用户已被禁用");
		} catch (ExcessiveAttemptsException e) {
			// 尝试认证次数多余系统指定次数 ...
			result.setStatus(HttpStatus.UNAUTHORIZED.value());
			result.setMsg("登录次数过多");
		} catch (AuthenticationException e) {
			// 其他自定义异常
			result.setStatus(HttpStatus.UNAUTHORIZED.value());
			result.setMsg(e.getMessage());
		}
		
		return result;
	}

	/**
	 * 检测验证码
	 * @param session
	 * @param request
	 */
	private void verifyCode(HttpSession session, HttpServletRequest request) {
		String rightCode = (String) session.getAttribute(Constant.RANDOMCODE);
		String userCode = request.getParameter(Constant.RANDOMCODE);
		
		if (userCode == null  || !userCode.equalsIgnoreCase(rightCode)) {
			throw new AuthenticationException("验证码错误");
		}
	}
	
}
