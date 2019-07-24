package com.shiro.security;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.entity.Menu;
import com.entity.Role;
import com.entity.User;
import com.service.RoleMenuService;
import com.service.UserRoleService;
import com.service.UserService;

/**
 * 自定义Shiro身份认证和授权处理
 * @author ljh
 */
public class CustomRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	/**
	 * 身份验证--登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        
        User user = userService.getUserByName(userName);
        if (null == user) {
        	throw new UnknownAccountException("此用户不存在");
		}
        
        ByteSource credentialsSalt = ByteSource.Util.bytes(userName);//使用账号作为盐值
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
        return info;
	}
	
	/**
	 * 身份授权--权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (null == principals) {
			return null;
		}
		
		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principals);
            SecurityUtils.getSubject().logout();
            return null;
        }

		//获取用户
		User user =(User) SecurityUtils.getSubject().getPrincipal();
		
		List<Role> roleList=userRoleService.getRoleListByUserId(user.getId());
		List<Menu> menuList=roleMenuService.getMenuListByUserId(user.getId());
		
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        
        //加入角色
        for (Role role : roleList) {
        	info.addRole(role.getRole());
		}
        
        // 加入权限
        for (Menu menu : menuList) {
        	if (null != menu.getPermission() && !"".equals(menu.getPermission())) {
        		for (String permission : menu.getPermission().split(",")) {
            		info.addStringPermission(permission);
    			}
			}
		}
        
        return info;
	}

	

	
}
