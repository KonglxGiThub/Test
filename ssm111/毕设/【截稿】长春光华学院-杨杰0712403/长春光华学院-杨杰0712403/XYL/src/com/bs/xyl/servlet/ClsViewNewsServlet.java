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

import com.bs.xyl.bean.News;
import com.bs.xyl.dao.NewsDao;
import com.bs.xyl.util.Constants;

public class ClsViewNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClsViewNewsServlet() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(Constants.MY_CONTENTTYPE);
		request.setCharacterEncoding(Constants.MY_ENCODING);
		int class_id = Integer.valueOf(request.getParameter("class_id"));
		NewsDao dao = new NewsDao();
		List<News> list = dao.viewNews(class_id);
		Map<String, Object> map = new HashMap<String, Object>();
		if(list.size()>0){
			map.put("rows", list);
			map.put("total", list.size());
		}else{
			map.put("rows", list);
			map.put("total", 0);
			map.put("errorMsg", "现在没有数据");
		}
		String dataJson = JSONObject.fromObject(map).toString();
		System.out.println(dataJson);
		response.getWriter().write(dataJson);
	}

}
