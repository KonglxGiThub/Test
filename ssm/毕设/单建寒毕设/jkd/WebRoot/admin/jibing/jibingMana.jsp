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
           function jibingDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/jibingDel.action?id="+id;
               }
           }
           
           function jibingAdd()
           {
                 var url="<%=path %>/admin/jibing/jibingAdd.jsp";
				 window.location.href=url;
           }
           
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="44" background="<%=path %>/img/tbg.gif">&nbsp;疾病信息管理&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="5%">序号</td>
					<td width="10%">疾病名称</td>
					<td width="10%">症状</td>
					<td width="10%">人员姓名</td>
					
					<td width="10%">患病时间</td>
					<td width="10%">责任医生</td>
					<td width="10%">操作</td>
		        </tr>	
				<s:iterator value="#request.jibingList" id="jibing" status="ss">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#ss.index+1"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#jibing.mingcheng"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#jibing.zhengzhuang"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#jibing.renyuan.xingming"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#jibing.huanshijian"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#jibing.yisheng"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a href="#" onclick="jibingDel(<s:property value="#jibing.id"/>)" class="pn-loperator">删除</a>
					</td>
				</tr>
				</s:iterator>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="添加" style="width: 80px;" onclick="jibingAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
