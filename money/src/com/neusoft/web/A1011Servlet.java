package com.neusoft.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.services.A1010Services;
import com.neusoft.system.tools.Tools;

@WebServlet("/a1011")
public class A1011Servlet extends HttpServlet 
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
         try
         {
        	 request.setAttribute("ocaab004", Tools.getOptions("AAB004"));
        	 
			 //��д����
			 String aab002=request.getParameter("aab002");  //ժҪ
			 String aab003=request.getParameter("aab003");  //���
			 String aab004=request.getParameter("aab004");  //���
			 String aab005=request.getParameter("aab005");  //��������
			 String aab007=request.getParameter("aab007");  //��ע
			 String val[]={aab002,aab003,aab004,aab005,aab007};
			 
			 A1010Services services=new A1010Services();
			 String msg=services.add(val)?"��ӳɹ�!":"���ʧ��!";
			 request.setAttribute("msg", msg);
		} 
        catch (Exception e) 
        {
        	request.setAttribute("msg", "�������!");
			e.printStackTrace();
		}
		 
        request.getRequestDispatcher("/A1011.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		this.doGet(request, response);
	}

}
