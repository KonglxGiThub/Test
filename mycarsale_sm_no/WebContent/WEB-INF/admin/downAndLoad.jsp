<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="download.action?img=D:\test/tietu.gif">下载</a><br>
<form action="upload.action" method="post" enctype="multipart/form-data">
<input type="file" name="pic"><br>
<input type="submit" value="上传">
</form>
</body>
</html>