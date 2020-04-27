<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/content.css">
    <title>产品列表</title>
</head>
<body>
    <h3 align="center">产品列表</h3>
    <table align="center" width="70%" border="1px">
        <tr>
            <th>商品编码</th>
            <th>商品名称</th>
            <th>计量单位</th>
            <th>进价</th>
            <th>售价</th>
            <th>库存数量</th>
            <th></th>
        </tr>
        <c:forEach items="${pageBean.beanList}" var="product">
            <tr>
                <td>${product.barcode}</td>
                <td>${product.name}</td>
                <td>${product.units}</td>
                <td>${product.purchasePrice}</td>
                <td>${product.salePrice}</td>
                <td>${product.inventory}</td>
                <td>
                    <a class="button" href="<c:url value='/ProductServlet?method=preEdit&id=${product.id}'/> ">编辑</a>
                    <a class="button" href="<c:url value='/ProductServlet?method=delete&id=${product.id}'/> ">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <center>
        <a class="button" href="${pageBean.url}&pageCode=1">FirstPage</a>

        <c:if test="${pageBean.pageCode>1}">
            <a class="button" href="${pageBean.url}&pageCode=${pageBean.pageCode-1}">PreviousPage</a>
        </c:if>

        <c:choose>
            <c:when test="${pageBean.totalPage<=10}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${pageBean.totalPage}"/>
            </c:when>
            <c:otherwise>
                <c:set var="begin" value="${pageBean.pageCode-5}"/>
                <c:set var="end" value="${pageBean.pageCode+4}"/>

                <c:if test="${begin<1}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="10"/>
                </c:if>

                <c:if test="${end>pageBean.totalPage}">
                    <c:set var="begin" value="${pageBean.totalPage-9}"/>
                    <c:set var="end" value="${pageBean.totalPage}"/>
                </c:if>
            </c:otherwise>
        </c:choose>

        <c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i eq pageBean.pageCode}">
                    [${i}]
                </c:when>
                <c:otherwise>
                    <a class="button" href="${pageBean.url}&pageCode=${i}">[${i}]</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${pageBean.pageCode<pageBean.totalPage}">
            <a class="button" href="${pageBean.url}&pageCode=${pageBean.pageCode+1}">NextPage</a>
        </c:if>
        <a class="button" href="${pageBean.url}&pageCode=${pageBean.totalPage}">LastPage</a>
    </center>
</body>
</html>
