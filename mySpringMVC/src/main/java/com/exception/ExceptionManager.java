package com.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 自定义异常管理
 * @author ljh
 */
@ControllerAdvice
public class ExceptionManager {
	
	/**
	 * 未授权异常处理
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = UnauthorizedException.class)
	public String unauthorizedExceptionHandler(HttpServletRequest request, Exception e){
		return "error/unauthorized";
	}
}
