<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/public/taglib.jsp"%>
<html>
<head>
<title>编辑角色</title>
</head>
<body>
	<div class="form-group center-block" style="margin-top: 5%">
		<form id="editRoleForm" class="form-horizontal" role="form">
			<input type="hidden" name="id" value="${role.id}">
			<div class="form-group">
				<label for="name" class="col-sm-offset-2 col-sm-2 control-label">角色名称</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-user fa-fw" aria-hidden="true"></i>
					</span> 
					<input type="text" class="form-control" id="name" name="name" value="${role.name}" required maxlength="16" placeholder="请输入姓名">
				</div>
			</div>
			
			<div class="form-group">
				<label for="loginName" class="col-sm-offset-2 col-sm-2 control-label">英文名称</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-user-secret fa-fw" aria-hidden="true"></i>
					</span> 
					<input type="text" class="form-control" id="role" name="role" value="${role.role}" required maxlength="16" placeholder="请输入登录名称">
				</div>
			</div>
			
			<div class="form-group">
				<label for="phone" class="col-sm-offset-2 col-sm-2 control-label">角色描述</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-text-width" aria-hidden="true"></i>
					</span> 
					<textarea name="describe" class="form-control" required maxlength="50" placeholder="请输入角色描述" rows="3" cols="10">${role.describe}</textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label for="email" class=" col-sm-offset-2 col-sm-2 control-label">权限</label>
				<div class="col-sm-5 input-group">
					<div id="menuTreeView">
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-8">
					<button type="submit" class="btn btn-success">确认</button>
					<button type="button" onclick="closeAllLayer()" class="btn btn-info">取消</button>
				</div>
			</div>
		</form>
	</div>
</body>


<script type="text/javascript">

$(function(){
	//绑定添加角色表单
	$("#editRoleForm").validate({
	   rules:{
		   	name:{
		   		required:true,
		   		maxlength:16
		   		
            },
            role:{
            	required:true,
            	maxlength:16
            },
            describe:{
            	maxlength:50
            }
		},
		submitHandler: function(form){
			editRole();
		},
		errorPlacement: function(error, element) {//错误显示的位置
			layer.tips(error[0].outerText, element, {
				  tips: [2, '#FF0000'], //1-上，2-右,3-下，4-左.还可配置颜色
				  tipsMore: true//不销毁之前的
			});
		}
	});
	
	//加载并回显角色权限菜单树形结构
	initMenuTreeView("menuTreeView","${role.id}");
});

/**
 * 编辑角色
 */
function editRole(){
	layer.load(0);
	$("#editRoleForm").ajaxSubmit({
		url:"${ctx}/role/updateRole",
		type:"post",
		beforeSubmit:function(formData,jqFrom,options){
			var ids = getSelectedNode("menuTreeView");
			if(null != ids && "" != ids){
				var idstr = "";
				for(var i in ids){
					idstr += ids[i].id +",";
				}
				idstr = idstr.substring(0,idstr.length-1);
				var menuId={};
				menuId.name="menuIds";
				menuId.value=idstr;
				formData.push(menuId);
			}
			
		},
		success:function(data){
			closeAllLayer();
			parent.layer.msg(data.msg, {icon: data.code, time: 2000});
			parent.initRoleTable();
		},
		error:function(){
			layer.closeAll();
			layer.alert("系统异常,请稍候重试");
		}
	});
}

</script>
</html>