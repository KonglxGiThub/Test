<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.neusoft.services.*"%>
<%
   String sname=request.getParameter("sname");       //����
   String snumber=request.getParameter("snumber");   //ѧ��
   String sex=request.getParameter("sex");           //�Ա� 
   String minzu=request.getParameter("minzu");       //����
   String shengri=request.getParameter("shengri");   //����
   //out.print(shengri);   
   String zhuanye=request.getParameter("zhuanye");   //רҵ
   String beizhu=request.getParameter("beizhu");     //��ע
   String val[]={sname,snumber,shengri,sex,minzu,zhuanye,beizhu};
   
   StudentServices services=new StudentServices();
   String msg=services.addStudent(val)?"��ӳɹ�!":"���ʧ��!";
   request.setAttribute("msg",msg);
%>
<jsp:forward page="/main.jsp"></jsp:forward>
