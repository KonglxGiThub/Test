<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.neusoft.services.*" %>
<%@ page import="com.neusoft.system.tools.*" %>
<%
    //1.��addҳ���ȡmainҳ��ؼ���ֵ:��ȡ��ʽΪ,ͨ�����ñ���request����ȡֵ
    String lyr=Tools.encoding(request.getParameter("lyr"));
    String lybt=Tools.encoding(request.getParameter("lybt"));
    String lynr=Tools.encoding(request.getParameter("lynr"));
    
    //2.�ڵ�ǰҳ�����mainҳ�������:out.println() jsp��ҳ�������� 
    //out.println("lyr="+lyr+"<br>");
    //out.println("lybt="+lybt+"<br>");
    //out.println("lynr="+lynr+"<br>");
    
    LyBookServices services=new LyBookServices();
    String val[]={lyr,lybt,lynr};
    String msg=services.addLybook(val)?"���Գɹ�":"����ʧ��!";
    //out.print(msg);
    
    //1.��request�ж�����Ϣ��ʾ����
    request.setAttribute("msg",msg);
    
    List rows=services.query();
    if(rows.size()>0)
    {
    	request.setAttribute("rows",rows);
    }
%>
<jsp:forward page="/main.jsp"></jsp:forward>
