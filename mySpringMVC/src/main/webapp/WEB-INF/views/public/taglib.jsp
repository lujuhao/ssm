<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>

<script type="text/javascript">
var ctx="${ctx}";
var ctxStatic="${ctxStatic}";
</script>

<%-- ----------------------------------引入公共css---------------------------------------- --%>
<%-- 定义页面图标 --%>
<link rel="shortcut icon" href="${ctxStatic}/img/web-icon.jpg" type="image/x-icon" />

<%-- bootstrap --%>
<link href="${ctxStatic}/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>

<%-- font-awesome --%>
<link href="${ctxStatic}/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>

<%-- bootstrap-treeview --%>
<link href="${ctxStatic}/bootstrap-treeview/bootstrap-treeview.min.css" rel="stylesheet" type="text/css">

<%-- 页面统一风格的css --%>
<link href="${ctxStatic}/css/public.css" rel="stylesheet" type="text/css"/>


<%-- ----------------------------------引入公共js----------------------------------------- --%>
<%-- JQuery --%>
<script type="text/javascript" src="${ctxStatic}/js/jquery-3.4.1.js"></script>

<%-- Ajax-Form --%>
<script type="text/javascript" src="${ctxStatic}/js/jquery.form.js"></script>

<%-- JQuery-Validate表单验证 --%>
<script type="text/javascript" src="${ctxStatic}/jquery-validate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery-validate/messages_zh.js"></script>

<%-- layer --%>
<script type="text/javascript" src="${ctxStatic}/layer/layer.js"></script>

<%-- bootstrap --%>
<script type="text/javascript" src="${ctxStatic}/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctxStatic}/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="${ctxStatic}/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>

<%-- bootstrap-treeview --%>
<script type="text/javascript" src="${ctxStatic}/bootstrap-treeview/bootstrap-treeview.min.js"></script>

<%-- 公用的自定义js --%>
<script type="text/javascript" src="${ctxStatic}/js/public.js"></script>
<%--treeview自定义函数 --%>
<script type="text/javascript" src="${ctxStatic}/js/treeview.js"></script>
