package com.bs.xyl.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.bs.xyl.bean.News;
import com.bs.xyl.dao.NewsDao;
import com.bs.xyl.util.Constants;
import com.bs.xyl.util.Validate;

public class ClsNewNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClsNewNewsServlet() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(Constants.MY_CONTENTTYPE);
		request.setCharacterEncoding(Constants.MY_ENCODING);
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		Map<String, Object> map = new HashMap<String, Object>();
		if(title.length()>15||title.length()<5){
			map.put("errorMsg", "������5~15����֮�䣬��ȷ�Ϻ������²�����");
		}else if(text.length()<10){
			map.put("errorMsg", "�������ݲ�������10���֣���ȷ�Ϻ������²�����");
		}else{
			int class_id = Integer.valueOf(request.getParameter("class_id"));
			Validate vd = new Validate();
			String time = vd.getSystemDate();
			NewsDao dao = new NewsDao();
			String result = dao.login("t_news", "text", "class_id", text);
			if (text.equals(result)) {
				map.put("errorMsg", "�������ӵ������Ѿ����ڣ���ȷ�Ϻ������²�����");
			} else {
				News news = new News(title, text, time,1,class_id);
				int num = dao.clsAddNew(news);
				if (num == 1) {
					response.sendRedirect("ClsViewNewsServlet?class_id="+class_id);
				} else {
					map.put("errorMsg", "����ʧ�ܣ���ȷ�Ϻ������²�����");
				}
			}
		}
		String dataJson = JSONObject.fromObject(map).toString();
		response.getWriter().write(dataJson);
	}

}
