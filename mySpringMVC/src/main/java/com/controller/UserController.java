package com.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.entity.User;
import com.service.UserService;
import com.util.FileUtil;
import com.vo.Page;
import com.vo.ReturnResult;

/**
 * 用户Controller
 * @author ljh
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户列表页面
	 * @return
	 */
	@RequestMapping("userListIndex")
	@RequiresPermissions("user:list")
	public String userListIndex(){
		return "user/userList";
	}
	
	/**
	 * 添加用户列表页面
	 * @return
	 */
	@RequestMapping("addUserIndex")
	public String addUserIndex(){
		return "user/addUser";
	}
	
	/**
	 * 编辑用户列表页面
	 * @param userId 用户id
	 * @param model
	 * @return
	 */
	@RequestMapping("editUserIndex")
	public String editUserIndex(String userId,Model model){
		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "user/editUser";
	}
	
	/**
	 * 分页查询用户列表
	 * @param user 过滤条件
     * @param pageNo 页码
     * @param pageSize 页宽
	 * @return
	 */
	@RequestMapping("selectUserByPage")
	@ResponseBody
	public Map<String, Object> selectUserByPage(HttpServletRequest request,User user,int pageNo,int pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		Page<User> pageInfo = userService.selectUserByPage(new Page<User>(request),user);
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	/**
	 * 添加用户
	 * @param user 用户信息
	 * @param returnResult 返回信息
	 * @return
	 */
	@RequestMapping("insertUser")
	@ResponseBody
	public ReturnResult insertUser(User user,ReturnResult returnResult){
		try {
			userService.insert(user);
			returnResult.setMsg("添加用户:"+user.getName()+"成功");
		} catch (Exception e) {
			returnResult.setCode(0);
			returnResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			returnResult.setMsg("添加用户失败");
			e.printStackTrace();
		}
		return returnResult;
	}
	
	/**
	 * 根据用户id删除用户
	 * @param userId 用户id
	 * @param returnResult 返回信息
	 * @return
	 */
	@RequestMapping("deleteUser")
	@ResponseBody
	public ReturnResult deleteUser(String userId,ReturnResult returnResult){
		try {
			int num = userService.deleteByID(userId);
			returnResult.setMsg("删除"+num+"个用户成功");
		} catch (Exception e) {
			returnResult.setCode(0);
			returnResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			returnResult.setMsg("删除用户失败");
			e.printStackTrace();
		}
		
		return returnResult;
	}
	
	
	/**
	 * 编辑用户
	 * @param user 用户信息
	 * @param multipartFile 上传的头像文件
	 * @param request
	 * @param returnResult 返回信息
	 * @return
	 */
	@RequestMapping("updateUser")
	@ResponseBody
	public ReturnResult updateUser(User user,@RequestParam(value="img",required=false)MultipartFile multipartFile,HttpServletRequest request,ReturnResult returnResult){
		try {
			String filePath = FileUtil.uploadUserHeadImg(multipartFile,request);
			user.setHeadImg(filePath);
			userService.update(user);
			returnResult.setMsg("编辑用户:"+user.getName()+"成功");
		} catch (Exception e) {
			returnResult.setCode(0);
			returnResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			returnResult.setMsg("编辑用户失败");
			e.printStackTrace();
		}
		
		return returnResult;
	}
	
	/**
	 * 根据用户id获取用户
	 * @param userId 用户id
	 * @return
	 */
	@RequestMapping("getUserById")
	@ResponseBody
	public User getUserById(String userId){
		return userService.getUserById(userId);
	}
}
