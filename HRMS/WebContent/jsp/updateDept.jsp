<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>更新部门数据</title>
		<link rel="stylesheet" type="text/css" href="../css/style.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>

	<body>
	<form action="updateDeptSubmit.action" method="post">
		<!-- 页面头部 -->
		<table width="950" border="0" align="center" cellpadding="0"
	cellspacing="0">
          <tr>
            <td height="80" bgcolor="#FFFFFF"><img src="../img/bannal.jpg" width="950"
				height="80"> </td>
          </tr>
          <tr>
            <td height="24" align="right" bgcolor="#FFFFFF">
			<a href="hello.action">首页</a>
			<a href="showDept.action">部门查询</a>
			<a href="JobsViewServlet.html">职务查询</a>
			<a href="EmpViewServlet.html">员工查询</a>			
			<a href="logout.jsp.html">退出登陆</a>
			</td>
          </tr>
          <tr>
            <td height="24" align="right" bgcolor="#0099CC"> 当前用户：admin 身份：
              
              管理员 </td>
          </tr>
        </table>
		<!-- 页面内容 -->
		<table border="0" width="950" height="350" bgcolor="#ffffff"
			align="center">
			<tr>
				<td align="center" valign="top">

					<form name="form1" method="post" action="UpdateDeptServlet">
						<table width="500" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#CCCCCC">
							<tr>
								<td height="24" colspan="2" align="center" bgcolor="#FFCC00">
									修改部门信息
								</td>
							</tr>
							<tr>
								<td width="120" height="24" bgcolor="#FFFFFF">
									部门编号
								</td>
								<td width="357" height="24" bgcolor="#FFFFFF">
									<input name="departmentId" type="text" id="department_id"
										readonly="readonly"
										value="${did }">
									*
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									部门名称
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="departmentName" type="text" id="department_name"
										value="${dname }">
									*
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									部门地址
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="locationName" type="text" id="location_name"
										value="${requestScope.dloc }">
									*
								</td>
							</tr>
							<tr>
								<td height="24" colspan="2" align="center" bgcolor="#FFFFFF">
									<input type="submit" name="Submit" value="更新">
									<input type="button" name="Submit2" value="取消"
										onclick="history.back();">${msg }
								</td>
							</tr>
						</table>
					</form>

				</td>
			</tr>
		</table>
</form>
		<!-- 页面底部 -->
		
<table width="950" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td><hr></td>
  </tr>
  <tr>
    <td align="center">©版权所有</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
	</body>
</html>