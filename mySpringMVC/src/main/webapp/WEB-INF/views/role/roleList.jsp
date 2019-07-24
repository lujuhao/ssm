<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/public/taglib.jsp"%>
<html>
<head>
<title>角色列表</title>
</head>
<body>
	<div class="search-from">
		<form class="form-inline" role="form">
			<div class="form-group search-from-left">
				<label class="control-label" for="searchName">名称</label> 
				<input type="text" class="form-control" id="searchName" placeholder="输入角色名称查询">

				<button class="btn btn-info" onclick="initRoleTable()">查询</button>
				<button class="btn btn-default" onclick="resetSearch()">重置</button>
			</div>

			<div class="form-group search-from-right">
				<button class="btn btn-primary" onclick="openAddRoleLayer()">添加角色</button>
				
				<button class="btn btn-danger" onclick="batchDeleteRole()">删除角色</button>
			</div>
		</form>
	</div> 
	
	<table id="roleTable">
		
	</table>
	
</body>


<script type="text/javascript">

$(function(){
	initRoleTable();
});

/**
 * 加载用户列表
 */
function initRoleTable(){
	 $("#roleTable").bootstrapTable("destroy");//先销毁
	 $("#roleTable").bootstrapTable({
         url:"${ctx}/role/selectRoleByPage",  //请求后台的URL（*）
         method: "post",                     //请求方式（*）
         toolbar: "#toolbar",                //工具按钮用哪个容器
         striped: true,                      //是否显示行间隔色
         cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
         pagination: true,                   //是否显示分页（*）
         sortable: true,                     //是否启用排序
         sortOrder: "asc",                   //排序方式
         queryParams: function(params){		//传递参数（*）
        	 var temp = {
        		 	name: $("#searchName").val(),
             		pageNo: params.offset,	//页码
             		pageSize: params.limit,	//页宽
             		orderField: params.sort,//排序字段
             		orderType: params.order//排序方式
            };
         
            return temp;
         },
         sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
         pageNumber: 1,                       //初始化加载第一页，默认第一页
         pageSize: 10,                       //每页的记录行数（*）
         pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
         smartDisplay:false,				//显示每页展示多少条数据
         search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
         showRefresh: true, 					//是否显示刷新按钮
         contentType: "application/x-www-form-urlencoded",
         strictSearch: true,
         showColumns: true,                  //是否显示所有的列
         minimumCountColumns: 2,             //最少允许的列数
         clickToSelect: true,                //是否启用点击选中行
         //height: 1000,                        //行高，如果没有设置height属性，表格自动根据记录条数设置表格高度
         uniqueId: "id",                     //每一行的唯一标识，一般为主键列
         showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
         cardView: false,                    //是否显示详细视图
         detailView: false,                   //是否显示父子表
         columns: [
                {
               	 	checkbox: true,visible: true ,align:'center',valign:'middle'   
                },//开启复选框
	            {
	            	title: '中文名称',field: 'name',align:'center',valign:'middle',sortable:true
	            }, 
	            {
	            	title: '英文名称',field: 'role',align:'center',valign:'middle',sortable:true
	            }, 
	            {
	            	title: '描述',field: 'describe' ,align:'center',valign:'middle',sortable:true
	            }, 
	            {
	            	title: '操作',field: 'operate',align:'center',valign:'middle',
	                formatter: function(value, row, index){//自定义方法，添加操作按钮
	                		return [
		                	        '<a onclick="openEditRoleLayer('+"'"+''+row.id+''+"'"+')" class="btn btn-info" href="#"><i class="fa fa-pencil-square-o"></i> 编辑</a>',
		                	        '&nbsp;<a onclick="deleteRole('+"'"+''+row.id+''+"'"+')" class="btn btn-danger" href="#"><i class="fa fa-trash-o fa-lg"></i> 删除</a>'
	                	        ].join(''); 
	                		
	                } 
	            },
         ],
     });
}

/**
 * 打开添加角色信息模态框
 */
function openAddRoleLayer(){
	layer.open({
		type: 2,//样式（0-信息框（默认），1-打开div（页面），2-打开网页（iframe）,3-加载层，4-tips层）
        title:"角色信息",
        shadeClose: true,
        shade: 0.6,
        area: ['40%', '90%'],
        content: "${ctx}/role/addRoleIndex",//页面，图片，div
	});
}

/**
 * 打开编辑角色信息模态框
 * @param userId 用户id
 */
function openEditRoleLayer(roleId){
	layer.open({
		type: 2,//样式（0-信息框（默认），1-打开div（页面），2-打开网页（iframe）,3-加载层，4-tips层）
        title:"角色信息",
        shadeClose: true,
        shade: 0.6,
		area: ['40%', '90%'],
        content: "${ctx}/role/editRoleIndex?roleId="+roleId,//页面，图片，div
	});
}

/**
 * 删除角色
 * @param userId 用户id
 */
function deleteRole(roleId){
	layer.confirm("确认删除此角色吗？",{title:"确认删除",btn:['是','否'],offset: 'm'},
			function(index){//执行是
				layer.close(index);
				layer.load(0);
				$.ajax({
					url:"${ctx}/role/deleteRole",
					type:"post",
					dataType:"json",
					data:{
						roleId:roleId
					},
					success:function(data){
						layer.closeAll();
						layer.msg(data.msg, {icon: data.code, time: 2000});
						$("#roleTable").bootstrapTable("refresh");
					},
					error:function(){
						layer.closeAll();
						layer.alert("系统异常,请稍候重试");
					}
				});
				
			},
			function(index){//执行否
				layer.close(index);
			}
	);
}


/**
 * 批量删除角色
 */
function batchDeleteRole(){
	var selectRow = $("#roleTable").bootstrapTable('getSelections');
	
	if (selectRow.length < 1) {
		 layer.msg('请选择要删除的角色',{icon:0,time:2000})
		 return;
	}
	
	layer.confirm('确认删除选中的角色？', {
		  title:"删除角色",
		  btn: ['是','否'] //按钮
	}, function(index){
		layer.close(index);
		layer.load(0);//等待框
		var ids = "";
		for(var i=0;i<selectRow.length;i++){
			ids = ids + selectRow[i].id + ",";
		}
		ids = ids.substr(0, ids.length-1);
		$.ajax({
			url:"${ctx}/role/deleteRole",
			type:"post",
			dataType:"json",
			data:{
				roleId:ids
			},
			success:function(data){
				layer.closeAll();
				layer.msg(data.msg, {icon: data.code, time: 2000});
				$("#roleTable").bootstrapTable("refresh");
			},
			error:function(){
				layer.closeAll();
				layer.alert("系统异常,请稍候重试");
			}
		});
	}, function(index){
		layer.close(index); 
	});
}
</script>
</html>