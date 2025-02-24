package com.bs.xyl.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.bs.xyl.bean.Student;
import com.bs.xyl.dao.StuDao;
import com.bs.xyl.util.Constants;

public class StuSclViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StuSclViewServlet() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(Constants.MY_CONTENTTYPE);
		request.setCharacterEncoding(Constants.MY_ENCODING);
		StuDao dao = new StuDao();
		String name=(String)request.getParameter("name");
		String stu_no=(String)request.getParameter("stu_no");
		List<Student> list = dao.searchAllScl(stu_no, name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", list.size());
		String dataJson = JSONObject.fromObject(map).toString();
		response.getWriter().write(dataJson);
	}

}
