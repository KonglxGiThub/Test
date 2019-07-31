package com.bs.xyl.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.bs.xyl.dao.MessageDao;
import com.bs.xyl.util.Constants;

public class AdminDelMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelMessageServlet() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(Constants.MY_CONTENTTYPE);
		request.setCharacterEncoding(Constants.MY_ENCODING);
		String ids = request.getParameter("id");
		MessageDao dao = new MessageDao();
		int num = dao.delete(ids);
		if (num > 0) {
			response.sendRedirect("AdminViewMessageServlet");
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("errorMsg", "�����쳣");
			String dataJson = JSONObject.fromObject(map).toString();
			response.getWriter().write(dataJson);
		}
	}

}
