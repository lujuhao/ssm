<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/public/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>小冷后台管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> 
addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>

<link href="${ctxStatic}/views/index/css/style.css" rel='stylesheet' type='text/css' />
<link href='${ctxStatic}/views/index/css/SidebarNav.min.css' media='all' rel='stylesheet' type='text/css'/>
<link href="${ctxStatic}/views/index/css/custom.css" rel="stylesheet">
<link href="${ctxStatic}/views/index/css/owl.carousel.css" rel="stylesheet"> 
<link href="${ctxStatic}/views/index/css/export.css" rel="stylesheet"  type="text/css" media="all" />
 
<script src="${ctxStatic}/views/index/js/index.js"></script> 
<script src="${ctxStatic}/views/index/js/modernizr.custom.js"></script>
<script src="${ctxStatic}/views/index/js/Chart.js"></script>
<script src="${ctxStatic}/views/index/js/metisMenu.min.js"></script>
<script src="${ctxStatic}/views/index/js/custom.js"></script>
<script src="${ctxStatic}/views/index/js/pie-chart.js"></script>
<script src="${ctxStatic}/views/index/js/owl.carousel.js"></script>
<script src="${ctxStatic}/views/index/js/amcharts.js"></script>
<script src="${ctxStatic}/views/index/js/serial.js"></script>
<script src="${ctxStatic}/views/index/js/export.min.js"></script>
<script src="${ctxStatic}/views/index/js/light.js"></script>
<script src="${ctxStatic}/views/index/js/jquery.nicescroll.js"></script>
<script src="${ctxStatic}/views/index/js/scripts.js"></script>
<script src="${ctxStatic}/views/index/js/SimpleChart.js"></script>
<script src='${ctxStatic}/views/index/js/SidebarNav.min.js'></script>
<script src="${ctxStatic}/views/index/js/classie.js"></script>
 
</head> 
<body class="cbp-spmenu-push">
	<div class="main-content">
		<!--左侧菜单 开始-->
		<div class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1">
			<aside class="sidebar-left"> 
				<nav class="navbar navbar-inverse">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".collapse" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span>
						</button>
						<h1>
							<a href="javascript:void(0);" class="navbar-brand menu-href" data-href="/dashBoard">
								<span class="fa fa-snowflake-o"></span> 小冷
								<span class="dashboard_text">It's not late to meet.</span>
							</a>
						</h1>
					</div>
					
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="sidebar-menu">
							<li class="header">主菜单</li>
							<!-- <li class="header">主菜单2</li>
							<li>
								<a href="#">
									<i class="fa fa-angle-right text-red"></i>
									<span>Important</span>
								</a>
							</li> -->
						</ul>
					</div>
				</nav> 
			</aside>
		</div>
		<!--左侧菜单 结束-->
 	
		<!-- 头部信息 开始 -->
		<div class="sticky-header header-section">
			<!-- 头部信息 -消息提醒开始 -->
			<div class="header-left">
				<!--toggle button start-->
				<button id="showLeftPush"><i class="fa fa-bars"></i></button>
				<!--toggle button end-->
				<div class="profile_details_left"><!--notifications of menu start -->
					<ul class="nofitications-dropdown">
						<!-- 信箱 -->
						<li class="dropdown head-dpdn">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-envelope"></i><span class="badge">1</span></a>
							<ul class="dropdown-menu">
								<li>
									<div class="notification_header">
										<h3>您有1条信息</h3>
									</div>
								</li>
								<li>
									<a href="#">
									   <div class="user_img">
									   		<img src="${ctxStatic}/img/web-icon.jpg" alt="">
									   </div>
									   <div class="notification_desc">
										<p>今晚8点LOL?</p>
										<p><span>2019-6-27 10:56:20</span></p>
										</div>
									   <div class="clearfix"></div>	
									</a>
								</li>
								<li>
									<div class="notification_bottom">
										<a href="#">查看全部信息</a>
									</div> 
								</li>
							</ul>
						</li>
						
						<!-- 提醒 -->
						<li class="dropdown head-dpdn">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-bell"></i><span class="badge blue">1</span></a>
							<ul class="dropdown-menu">
								<li>
									<div class="notification_header">
										<h3>您有1条提醒</h3>
									</div>
								</li>
								<li><a href="#">
									<div class="user_img"><img src="${ctxStatic}/img/defaultHeadImg.jpg" alt=""></div>
								   <div class="notification_desc">
									<p>在？？？</p>
									<p><span>2019-6-27 9:25:20</span></p>
									</div>
								  <div class="clearfix"></div>	
								 </a></li>
								 <li>
									<div class="notification_bottom">
										<a href="#">查看全部提醒</a>
									</div> 
								</li>
							</ul>
						</li>	
						
						<!-- 进度/任务信息 -->
						<li class="dropdown head-dpdn">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-tasks"></i><span class="badge blue1">1</span></a>
							<ul class="dropdown-menu">
								<li>
									<div class="notification_header">
										<h3>您有1个待办任务</h3>
									</div>
								</li>
								<li><a href="#">
									<div class="task-info">
										<span class="task-desc">升级荒古</span><span class="percentage">99%</span>
										<div class="clearfix"></div>	
									</div>
									<div class="progress progress-striped active">
										<div class="bar yellow" style="width:40%;"></div>
									</div>
								</a></li>
								<li>
									<div class="notification_bottom">
										<a href="#">查看全部任务</a>
									</div> 
								</li>
							</ul>
						</li>	
					</ul>
					<div class="clearfix"> </div>
				</div>
				<div class="clearfix"> </div>
			</div>
			<!-- 头部信息 -消息提醒结束 -->
			
			<!-- 头部信息 -用户信息 开始 -->
			<div class="header-right">
				<!--搜索 div 开始-->
			<!-- 	<div class="search-box">
					<form class="input">
						<input class="sb-search-input input__field--madoka" placeholder="Search..." type="search" id="input-31" />
						<label class="input__label" for="input-31">
							<svg class="graphic" width="100%" height="100%" viewBox="0 0 404 77" preserveAspectRatio="none">
								<path d="m0,0l404,0l0,77l-404,0l0,-77z"/>
							</svg>
						</label>
					</form>
				</div> -->
				<!--搜索 div 结束-->
				
				<div class="profile_details">		
					<ul>
						<li class="dropdown profile_details_drop">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
								<div class="profile_img">	
									<span class="prfil-img"><img src="${ctx}${user.headImg}" width="50px" height="50px" alt="用户头像"> </span> 
									<div class="user-name">
										<p>用户名称</p>
										<shiro:hasAnyRoles name="admin">
											管理员
										</shiro:hasAnyRoles>
										
										<shiro:hasAnyRoles name="user">
											普通用户
										</shiro:hasAnyRoles>
										<span>${user.name}</span>
									</div>
									<i class="fa fa-angle-down lnr"></i>
									<i class="fa fa-angle-up lnr"></i>
									<div class="clearfix"></div>	
								</div>	
							</a>
							<ul class="dropdown-menu drp-mnu">
								<li> <a href="#"><i class="fa fa-cog"></i> 用户设置</a> </li> 
								<li> <a href="#"><i class="fa fa-user"></i> 帐号信息</a> </li> 
								<li> <a href="logout"><i class="fa fa-sign-out"></i> 注销登录</a> </li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- 用户信息 结束 -->
				<div class="clearfix"> </div>				
			</div>
			<div class="clearfix"> </div>	
		</div>
		<!-- 头部信息结束 -->
		
		<iframe id="iframe" src="${ctx}/dashBoard" style="width: 100%;border:none;"></iframe>
		
		
		<!--页尾版权信息 开始-->
		<div class="footer">
		   <p>Copyright &copy; 2019.Company name All rights reserved.More Templates <a href="http://www.baidu.com/" target="_blank" title="百度一下">小冷</a>
		</div>
	    <!--页尾版权信息 结束-->
	</div>
		
</body>

</html>