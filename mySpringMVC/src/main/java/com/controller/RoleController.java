package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Menu;
import com.entity.Role;
import com.entity.User;
import com.service.RoleMenuService;
import com.service.RoleService;
import com.vo.Page;
import com.vo.ReturnResult;

/**
 * 角色Controller
 * @author ljh
 */
@Controller
@RequestMapping("/role/")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	/**
	 * 角色列表页面
	 * @return
	 */
	@RequestMapping("roleListIndex")
	@RequiresPermissions("role:list")
	public String userListIndex(){
		return "role/roleList";
	}
	
	/**
	 * 添加角色页面
	 * @return
	 */
	@RequestMapping("addRoleIndex")
	public String addRoleIndex(){
		return "role/addRole";
	}
	
	/**
	 * 编辑角色列表页面
	 * @param roleId 角色id
	 * @param model
	 * @return
	 */
	@RequestMapping("editRoleIndex")
	public String editRoleIndex(String roleId,Model model){
		Role role = roleService.getRoleById(roleId);
		model.addAttribute("role", role);
		return "role/editRole";
	}
	
	/**
	 * 分页查询角色列表
	 * @param role 过滤条件
     * @param pageNo 页码
     * @param pageSize 页宽
	 * @return
	 */
	@RequestMapping("selectRoleByPage")
	@ResponseBody
	public Map<String, Object> selectRoleByPage(HttpServletRequest request,Role role,int pageNo,int pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			Page<User> pageInfo = roleService.selectRoleByPage(new Page<User>(request),role);
			map.put("total", pageInfo.getTotal());
			map.put("rows", pageInfo.getList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 添加角色
	 * @param role 角色信息
	 * @param menuIds 菜单权限id
	 * @param returnResult 返回信息
	 * @return
	 */
	@RequestMapping("insertRole")
	@ResponseBody
	public ReturnResult insertRole(Role role,String menuIds,ReturnResult returnResult){
		try {
			String roleId = roleService.insert(role);
			roleMenuService.insertRoleMenu(roleId,menuIds);
			returnResult.setMsg("添加角色:"+role.getName()+"成功");
		} catch (Exception e) {
			returnResult.setCode(0);
			returnResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			returnResult.setMsg("添加角色失败");
			e.printStackTrace();
		}
		return returnResult;
	}
	
	/**
	 * 根据角色id删除角色
	 * @param roleId 角色id
	 * @param returnResult 返回信息
	 * @return
	 */
	@RequestMapping("deleteRole")
	@ResponseBody
	public ReturnResult deleteRole(String roleId,ReturnResult returnResult){
		try {
			int num = roleService.deleteById(roleId);
			returnResult.setMsg("删除"+num+"个角色成功");
		} catch (Exception e) {
			returnResult.setCode(0);
			returnResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			returnResult.setMsg("删除角色失败");
			e.printStackTrace();
		}
		
		return returnResult;
	}
	
	
	/**
	 * 编辑角色
	 * @param role 角色信息
	 * @param menuIds 权限菜单
	 * @param request
	 * @param returnResult 返回信息
	 * @return
	 */
	@RequestMapping("updateRole")
	@ResponseBody
	public ReturnResult updateRole(Role role,String menuIds,ReturnResult returnResult){
		try {
			roleMenuService.updateRoleMenu(role.getId(), menuIds);
			roleService.update(role);
			returnResult.setMsg("编辑角色:"+role.getName()+"成功");
		} catch (Exception e) {
			returnResult.setCode(0);
			returnResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			returnResult.setMsg("编辑角色失败");
			e.printStackTrace();
		}
		
		return returnResult;
	}
	
	/**
	 * 根据角色id获取角色
	 * @param roleId 用户id
	 * @return
	 */
	@RequestMapping("getRoleById")
	@ResponseBody
	public Role getRoleById(String roleId){
		return roleService.getRoleById(roleId);
	}
	
	/**
	 * 获取角色拥有的菜单权限
	 * @param roleId 角色id
	 * @return
	 */
	@RequestMapping("getRoleMenuList")
	@ResponseBody
	public List<Menu> getRoleMenuList(String roleId){
		return roleMenuService.getMenuListByRoleId(roleId);
		
	}
}
