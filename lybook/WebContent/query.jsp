<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.neusoft.services.*" %>
<%
   LyBookServices services=new LyBookServices();
   List rows=services.query();
   if(rows.size()>0)
   {
	   //����ѯ���Ľ������ĵ�request
	   request.setAttribute("rows",rows);
   }
   else
   {
	   request.setAttribute("msg","û����������!");
   }	   
%>
<jsp:forward page="/main.jsp"></jsp:forward>
