package com.bs.xyl.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bs.xyl.bean.Student;
import com.bs.xyl.dao.StuDao;

public class FileUploadFromEditServlet extends HttpServlet {
	private static final long serialVersionUID = 2598654810371467006L;

	private String saveDirectoryName;

	private static final String address = "D:\\workspace\\2016\\XYL\\WebRoot\\images\\upload\\";

	@Override
	public void init() throws ServletException {
		saveDirectoryName = getInitParameter("saveDirectoryName");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �����ַ�����
		request.setCharacterEncoding("UTF-8");

		// ����'DiskFileItemFactory'����
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

		// ����'ServletFileUpload'����
		ServletFileUpload servletFileUpload = new ServletFileUpload(
				diskFileItemFactory);

		try {
			// ��ȡHttp������Ϣ ('Request') �е������������ (������ͨ�����Լ��ϴ�������)
			List<FileItem> fileItems = servletFileUpload.parseRequest(request);
			Student stu = new Student();
			Class<?> objectClass = stu.getClass();
			// ѭ��ȡ��Http������Ϣ ('Request') �е������������
			for (FileItem fileItem : fileItems) {
				// �����Http������Ϣ�еĲ�������ͨ���� (���е�һ����ֵͨ)
				if (fileItem.isFormField()) {
					// ��ñ��е���ͨ����ֵ (�ϴ���, �ļ��ĵȼ�), ʹ��UTF-8�ı����ʽ��ȡ����ͨ������ֵ
					String requestOfValue = fileItem.getString("UTF-8");
					System.out.println("���е���ͨ����: �ϴ��˵�����|�ļ��ȼ� ("
							+ requestOfValue + "), ���е�����Ϊ'"
							+ fileItem.getFieldName() + "'");
					Class<?> type = null;
					// ��ñ��е���ͨ����������������Ӧ������
					Method method = null;
					String methodName = Change.getMethodName(fileItem
							.getFieldName());
					if (methodName.equals("setState")
							|| methodName.equals("setClass_id")
							|| methodName.equals("setId")) {
						type = Integer.class;
						method = objectClass.getMethod(methodName, type);
						method.invoke(stu, Integer.valueOf(requestOfValue));
					} else {
						type = String.class;
						method = objectClass.getMethod(methodName, type);
						method.invoke(stu, requestOfValue);
					}
				}
			}
			StuDao dao = new StuDao();
			int num = dao.update2(stu);
			if (num == 1) {
				response.sendRedirect("AdminViewStuServlet");
			}
			System.out.println("num:" + num);
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
