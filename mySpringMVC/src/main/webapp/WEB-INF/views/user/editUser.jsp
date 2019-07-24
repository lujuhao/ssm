<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/public/taglib.jsp"%>
<html>
<head>
<style type="text/css">
#img{
	display: none;
}
</style>
<title>编辑信息</title>
</head>
<body>
	<div class="form-group center-block" style="margin-top: 5%">
		<form id="editUserForm" class="form-horizontal"  role="form" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${user.id}">
			
			<div class="form-group">
				<label for="name" class="col-sm-offset-2 col-sm-2 control-label">头像</label>
				<div class="col-sm-5">
					<div id="headImgDiv">
						<img alt="点击更换用户头像" id="headImg" src="${ctx}/${user.headImg}" class="img-circle" width="100" height="100">
					</div>
					<input type="file" class="form-control" id="img" name="img">
				</div>
			</div>
			
			<div class="form-group">
				<label for="name" class="col-sm-offset-2 col-sm-2 control-label">姓名</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-user fa-fw" aria-hidden="true"></i>
					</span> 
					<input type="text" class="form-control" value="${user.name}" id="name" name="name" required maxlength="16" placeholder="请输入姓名">
				</div>
			</div>
			
			<div class="form-group">
				<label for="loginName" class="col-sm-offset-2 col-sm-2 control-label">登录名称</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-user-secret fa-fw" aria-hidden="true"></i>
					</span>
					<input type="text" class="form-control" value="${user.loginName}" id="loginName" name="loginName" required maxlength="16" placeholder="请输入登录名称">
				</div>
			</div>
			
			<div class="form-group">
				<label for="phone" class="col-sm-offset-2 col-sm-2 control-label">手机号码</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-phone fa-fw" aria-hidden="true"></i>
					</span> 
					<input type="text" class="form-control" value="${user.phone}" id="phone" name="phone" required maxlength="11" placeholder="请输入手机号码">
				</div>
			</div>
			
			<div class="form-group">
				<label for="email" class=" col-sm-offset-2 col-sm-2 control-label">邮箱</label>
				<div class="col-sm-5 input-group">
					<span class="input-group-addon">
						<i class="fa fa-envelope-o fa-fw" aria-hidden="true"></i>
					</span> 
					<input type="text" class="form-control" value="${user.email}" id="email" name="email" placeholder="请输入邮箱">
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
	bindFormValidate();//绑定编辑用户表单验证
	bindHeadImgClick();//绑定单击用户头像
});

/**
 * 绑定表单验证
 */
function bindFormValidate(){
	//自定义验证
 	jQuery.validator.addMethod("phoneRule", function(value, element) {
 		if(!(/^1[3456789]\d{9}$/.test(value))){
 			return false;
 		}else{
 			return true;
 		}
    }, "请输入正确的手机号码");
	
	//绑定添加用户表单
	$("#editUserForm").validate({
	   rules:{
            email:{
            	email:true
            },
            phone:{
            	phoneRule:true
            },
		},
		submitHandler: function(form){
			editUser();
		},
		errorPlacement: function(error, element) {//错误显示的位置
			layer.tips(error[0].outerText, element, {
				  tips: [2, '#FF0000'], //1-上，2-右,3-下，4-左.还可配置颜色
				  tipsMore: true//不销毁之前的
			});
		}
	});
}

/**
 * 绑定头像单击事件
 */
function bindHeadImgClick(){
	//点击图片时触发file元素
	$("#headImg").on("click",function(){
		$("#img").click();
	});
	
	//绑定file控件的变更事件以实现图片预览
	$("#img").change(function(e){
		previewImage(this,"headImg");
	});
}

/**
 * 编辑用户
 */
function editUser(){
	layer.load(0);
	
	var formData = new FormData();//构建formData元素
	var params=$("#editUserForm").serializeArray();//序列化表单参数
	$.each(params, function(i, field){//遍历参数
		 formData.append(field.name,field.value);
    });
	$("input[type='file']").each(function(idx,ele){
		if($(ele).get(0).files.length > 0){
			var fileName=$(ele).attr("name");
			var file=$(ele).get(0).files[0];
			formData.append(fileName,file);//获取上传的文件
		}
	});
	$.ajax({
		url:"${ctx}/user/updateUser",
		type:"post",
		// 告诉jQuery不要去处理发送的数据
		processData : false, 
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		data:formData,
		success:function(data){
			closeAllLayer();
			parent.layer.msg(data.msg, {icon: data.code, time: 2000});
			parent.initUserTable();
		},
		error:function(){
			layer.closeAll();
			layer.alert("系统异常,请稍候重试");
		}
	});
}

</script>
</html>