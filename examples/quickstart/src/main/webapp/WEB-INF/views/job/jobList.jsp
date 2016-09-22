<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>任务管理</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div class="row">
		<div class="span4 offset7">
			<form class="form-search" action="#">
				<label>职位：</label> <input type="text" name="search_LIKE_titile" class="input-medium" value="${param.search_LIKE_title}"> 
				<button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
	    </div>
	    <tags:sort/>
	</div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>序号</th>
		<th>公司</th>
		<th>职位</th>
		<th>类型</th
		<th>时间</th>
		<th>薪水</th>
		<th>管理</th></tr></thead>
		<tbody>
		<c:forEach items="${tasks.content}" var="task" begin="1" step="1" varStatus="status">
			<tr>
				<td><c:out value="${status.index}"/>
				<td><a href="${task.url}" target="_blank">${task.company}</a></td>
				<td><a href="${task.url}" target="_blank">${task.titile}</a></td>
				<td>${task.jobxz}</a></td>
				<td>${task.salary}</a></td>
				<td>${task.createDate}</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<tags:pagination page="${tasks}" paginationSize="20"/>

</body>
</html>
