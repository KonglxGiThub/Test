<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		
        <script language="javascript">
           function renyuanDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/renyuanDel.action?id="+id;
               }
           }
           
           function renyuanAdd()
           {
                 var url="<%=path %>/admin/renyuan/renyuanAdd.jsp";
				 window.location.href=url;
           }
           
           function renyuanDetail(id)
           {
                 var strUrl = "<%=path %>/renyuanDetail.action?id="+id;
                 var ret = window.showModalDialog(strUrl,"","dialogWidth:500px; dialogHeight:600px; dialogLeft: status:no; directories:yes;scrollbars:yes;Resizable=no;");
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="44" background="<%=path %>/img/tbg.gif">&nbsp;人员档案管理&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="5%">序号</td>
					<td width="10%">姓名</td>
					<td width="10%">性别</td>
					<td width="10%">年龄</td>
					<td width="10%">居住住址</td>
					<td width="10%">出生日期</td>
					<td width="10%">身份证</td>
					<td width="10%">操作</td>
		        </tr>	
				<s:iterator value="#request.renyuanList" id="renyuan" status="ss">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#ss.index+1"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#renyuan.xingming"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#renyuan.xingbie"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#renyuan.nianling"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#renyuan.dizhi"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#renyuan.chusheng"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#renyuan.shenfenzheng"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <a href="#" onclick="renyuanDetail(<s:property value="#renyuan.id"/>)" class="pn-loperator">详细信息</a>
						<a href="#" onclick="renyuanDel(<s:property value="#renyuan.id"/>)" class="pn-loperator">删除</a>
					</td>
				</tr>
				</s:iterator>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="添加" style="width: 80px;" onclick="renyuanAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
