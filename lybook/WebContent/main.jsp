<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
  <head>
    <title>���Ա�</title>
    <script type="text/javascript">
    
      function onNext1()
      {
    	  with(document.forms[0])
    	  {
    		  action="/lybook/query.jsp";
    	  }
      }  
    
      function onNext2()
      {
    	  with(document.forms[0])
    	  {
    		  if(lyr.value=="")
    		  {
    			  alert("������ ������!");
    			  lyr.focus();
    			  return false;
    		  }
    		  else if(lybt.value=="")
    		  {
    			  alert(" ������ ���Ա���");
    			  lybt.focus();
    			  return false;
    		  }
    		  else if(lynr.value=="")
    		  {
    			  alert(" ������ ��������!");
    			  lynr.focus();
    			  return false;
    		  }
    	  }
      }
    </script>
  </head>
  <body>
  <%
     //Object msg=request.getAttribute("msg");
     //if(msg!=null)
     //{
    	// out.print(msg);	 
     //}
  %>
  ${msg }
  <br>
  <br>
    <form action="/lybook/add.jsp" method="post">
	   <table border="1" width="45%" align="center">
	     <caption>
	                ���԰�
	       <hr width="160">         
	     </caption>
	     <tr>
	       <td>������</td>
	       <td>
	         <input type="text" name="lyr" >
	       </td>
	     </tr>
	     <tr>
	       <td>����</td>
	       <td>
	         <input type="text" name="lybt">
	       </td>
	     </tr>
	     <tr>
	       <td valign="top">����</td>
	       <td>  
	          <textarea rows="4" cols="45" name="lynr"></textarea>
	       </td>
	     </tr>
	     <tr>
	       <td colspan="2" align="center">
	          <input type="submit" name="next" value="��ѯ" onclick="return  onNext1()">
	          <input type="submit" name="next" value="���" onclick="return onNext2()">
	       </td>
	     </tr>
	   </table>
	   
	   <BR>
	  <c:if test="${rows!=null}">
	  
		   <table border="1" width="45%" align="center">
		     <c:forEach var="INS" items="${rows }">
			      <tr>
			       <td>
			                 ������: ${INS.LYR } &nbsp;&nbsp;&nbsp;&nbsp;  
			                 ����:${INS.LYBT }   &nbsp;&nbsp;&nbsp;&nbsp;
			                 ʱ��:${INS.LYSJ }   &nbsp;&nbsp;&nbsp;&nbsp;
			        <a href="/lybook/del.jsp?lid=${INS.LID }">ɾ��</a>  <BR>
			                 ����: ${INS.LYNR } 
			       </td>
		       </c:forEach>
		     </tr>
		   </table>
	  </c:if> 
	</form>
  </body>
</html>
