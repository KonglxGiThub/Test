<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
 
 
<jsp:useBean id="sn" scope="page" class="com.bean.SystemBean" />
<jsp:useBean id="eb" scope="page" class="com.bean.EmpBean" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dir=sn.getDir();
%>
<HTML><HEAD><TITLE>��̨������</TITLE>
<LINK href="<%=basePath %><%=dir %>/images/Admin_Style.css" type=text/css rel=stylesheet>
<LINK href="<%=basePath %><%=dir %>/images/style.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="<%=path %>/admin/images/calendar.js"></script> 
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
</STYLE>
</HEAD>
<script type="text/javascript">
function sub()
{//name,sex,bir,departs,zhiwu,tel,address,remark
	if(document.form1.name.value==""
	||document.form1.co.value==""
	||document.form1.bir.value==""
	||document.form1.nr.value==""
	||document.form1.jine.value==""
	||document.form1.remark.value=="")
	{
		alert("������Ŀ������д��");
		//document.form1.title.focus();
		return false;
	}
	 
	 
	form1.submit();
}
</script>
<%
String message = (String)request.getAttribute("message");
	if(message == null){
		message = "";
	}
	if (!message.trim().equals("")){
		out.println("<script language='javascript'>");
		out.println("alert('"+message+"');");
		out.println("</script>");
	}
	request.removeAttribute("message");
%>
<%
	String username=(String)session.getAttribute("user");
	if(username==null){
		response.sendRedirect(path+"/error.jsp");
	}
	else{
		String method=request.getParameter("method");//name,sex,bir,departs,zhiwu,tel,address,remark,addtime
		String name = "";	
		String co="";
		String bir="";String nr="";String jine="";String remark="";
		String id=""; 
		 if(method.equals("upWx")){
			id=request.getParameter("id").trim();
			List newsList=eb.getWx(id);
			name=newsList.get(1).toString();co=newsList.get(2).toString();
			bir=newsList.get(3).toString();
			nr=newsList.get(4).toString();
			 jine=newsList.get(5).toString();
			
			 remark=newsList.get(6).toString();
		}		
		 
%>
<BODY  >
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top >
    <form name="form1" action="<%=basePath %>DepServlet" method="post" >
<table width='100%' cellspacing='1' cellpadding='3' bgcolor='#CCCCCC' class="tablewidth">
<tr class="head"> 
      <td colspan="2">     
<%
	if(method.trim().equals("addPay")){
%>
        ��� 
<%}else{%>
	   �޸� 
<%} %>
      </td>
    </tr> 
	
  <tr bgcolor='#FFFFFF'> <input type="hidden" name="method" value="<%=method %>"> <input type="hidden" name="id" value="<%=id %>">
    <td width='30%'><div align="right">ά�޷������ƣ�</div></td>
    <td ><input name="name" type="text" id="name" size="40" maxlength="100" value="<%=name %>"></td>
  </tr>
  <tr bgcolor='#FFFFFF'> 
    <td width='30%'><div align="right">ά�޷���λ��</div></td>
    <td ><input name="co" type="text" id="co" size="40" maxlength="100" value="<%=co %>"></td>
  </tr>
  <tr bgcolor='#FFFFFF'> 
    <td width='30%'><div align="right">ά�޷������ڣ�</div></td>
    <td ><input name="bir" type="text" id="bir" size="40" maxlength="100" value="<%=bir %>"  readonly onclick="SelectDate(this,'yyyy-MM-dd')"  this.Txt_Date.Attributes["onclick"] = "SelectDate(this,'yyyy-MM-dd')";>  </TD>
  </tr>
  <tr bgcolor='#FFFFFF'> 
    <td width='30%'><div align="right">ά�޷������ݣ�</div></td>
    <td ><input name="nr" type="text" id="nr" size="40" maxlength="100" value="<%=nr %>"></td>
  </tr>
  <tr bgcolor='#FFFFFF'> 
    <td width='30%'><div align="right">ά�޽�</div></td>
    <td ><input name="jine" type="text" id="jine" size="40" maxlength="100" value="<%=jine %>"></td>
  </tr>
  
   <tr bgcolor='#FFFFFF'> 
    <td width='30%'><div align="right">��ע��</div></td>
    <td ><textarea name=remark cols=40 rows=10><%=remark %></textarea></td>
  </tr>
  
  <tr bgcolor='#FFFFFF'> 
      <td colspan="2" align="center"> 
        <input class=mmcinb type='button' name='button' value='�ύ����' onclick='sub()'>
      </td>
    </tr>
	
</table>
</form>
   </TD>
	</TR>
  </TBODY>
</TABLE>
</BODY>
<%} %>
</HTML>
