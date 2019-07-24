$(function(){
	sideMeun();//收缩左侧菜单功能
	initUserMenu();//加载用户菜单
	$('.sidebar-menu').SidebarNav();
});

/**
 * 收缩左侧菜单功能
 */
function sideMeun(){
	 var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),
	 showLeftPush = document.getElementById( 'showLeftPush' ),
	 body = document.body;

	showLeftPush.onclick = function() {
		classie.toggle( this, 'active' );
		classie.toggle( body, 'cbp-spmenu-push-toright' );
		classie.toggle( menuLeft, 'cbp-spmenu-open' );
		disableOther( 'showLeftPush' );
	};
	
	function disableOther( button ) {
		if( button !== 'showLeftPush' ) {
			classie.toggle( showLeftPush, 'disabled' );
		}
	}
}

/**
 * 获取用户菜单
 */
function initUserMenu(){
	$("#iframe").css("height",$(window).height()-60);
	$(document).on("click",".menu-href",function(){
		var href = $(this).attr("data-href");
		$("#iframe").attr("src",ctx+href);
	});
	
	$.ajax({
		url:ctx+"/menu/getUserMenuList",
		type:"post",
		data:{},
		success:function(data){
			if(data != null && data != {}){
				var $li=$("<li class='treeview'></li>");
				for(var i in data){
					//构建一级菜单
					var dataParent = data[i];
					var $parent_href=$("<a href='javascript:void(0);'><i class='"+dataParent.icon+"'></i>"
									+"<span>"+dataParent.name+"</span>"
									+"<i class='fa fa-angle-left pull-right'></i></a>");
					$li.append($parent_href);
					
					//构建二级菜单
					if(dataParent.childMenuList.length > 0){
						var dataChild = dataParent.childMenuList;
						var $ul=$("<ul class='treeview-menu'></ul>");
						for(var j in dataChild){
							var $child_li=$("<li><a href='javascript:void(0);' class='menu-href' data-href='"+dataChild[j].url+"'>"
											+"<i class='"+dataChild[j].icon+"'></i>"
											+dataChild[j].name
											+"</a></li>");
							$ul.append($child_li);
							$li.append($ul);
						}
						
					}
				}
				$(".sidebar-menu").append($li);
				
			}
		},
		error:function(){
			layer.alert("获取用户菜单失败");
		}
	});
}

/**
 * 注销登录
 */
function logout(){
	location.href=ctx+"/logout";
}
