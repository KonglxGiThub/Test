<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(request.getAttribute("msg")!=null){
String m = (String)request.getAttribute("msg");%>
<%=m %>
<% }%>

	<form action="Login1Servlet" method="get">
    	姓名<input type="text" name="user"><br>
		密码<input type="password" name="pass"><br>
		爱好<select name="hoppy">
				<option value="1">读书</option>
				<option value="lol">lol</option>
				<option value="run">天天酷跑</option>
				<option value="playing">跑步</option>
          </select>
                   技术<input type="checkbox" name="com" value="java">java
           <input type="checkbox" name="com" value="web">web
           <input type="checkbox" name="com" value="oracle">oracle
		提交<input type="submit" value="登录">
	</form>
	<div></div>
	<a href="Login1Servlet?user='tom'&pass='123'">xxx</a>
</body>
</html>