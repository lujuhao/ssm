package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.entity.User;

/**
 * 登录拦截器
 * 集成shiro后，可不必在spring-mvc配置中配置此拦截器
 * @author ljh
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//从当前会话中获取user
		HttpSession session = request.getSession();
		User user =(User) session.getAttribute("user");
		
		if (null == user) {
			//如果是ajax请求响应头会有x-requested-with 
			if (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")){  
				 String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort()+request.getContextPath();
				
				//告诉ajax我是重定向
	            response.setHeader("REDIRECT", "REDIRECT");
	            //告诉ajax我重定向的路径
	            response.setHeader("CONTENTPATH", basePath+"/login");
	            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	        }else {
		        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
