<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>系统首页</title>
    <base target="welcome">
</head>
<body style="text-align: center">
        <h1>企业产品信息管理系统</h1>
        <a href="add.jsp" >添加产品</a>
        <a href="<c:url value='/ProductServlet?method=showAll'/> ">显示产品</a>
        <a href="query.jsp">查找产品</a>
</body>
</html>
