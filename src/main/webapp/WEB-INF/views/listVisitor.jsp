<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <table border="1" align="center" style="text-align: center">
        <tr><td>序号</td><td>名称</td><td>邮箱</td><td>状态</td><td>时间</td></tr>
        <c:forEach items="${visitorList}" var="visitor" varStatus="index">
            <tr><td>${index.index+1 }</td><td>${visitor.name }</td><td>${visitor.email }</td><td>${visitor.status }</td><td>${visitor.createTime }</td></tr>
        </c:forEach>
    </table>
</body>
</html>