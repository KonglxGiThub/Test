<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.neusoft.services.*" %>
<%
   //1.��ȡ���Լ�¼����ˮ��
   String lid=request.getParameter("lid");
   //out.print("lid=="+lid);
   //2.ʵ����Services
   LyBookServices services=new LyBookServices();
   //3.����ɾ������
   String msg=services.delete(lid)?"ɾ���ɹ�!":"ɾ��ʧ��";
   //3.����Ϣ����request
   request.setAttribute("msg",msg);
   
   //4.���¼�����һ������
   List rows=services.query();
   if(rows.size()>0)
   {
	   request.setAttribute("rows",rows);
   }
%>
<jsp:forward page="/main.jsp"></jsp:forward>
