<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.neusoft.services.*"%>
<%
   StudentServices services=new StudentServices();
   List<Map> rows=services.query();
   if(rows.size()>0)
   {
	   request.setAttribute("rows",rows);
   }
   else
   {
	   request.setAttribute("msg","û�з�������������!");
   }	   
%>
<jsp:forward page="/main.jsp"></jsp:forward>