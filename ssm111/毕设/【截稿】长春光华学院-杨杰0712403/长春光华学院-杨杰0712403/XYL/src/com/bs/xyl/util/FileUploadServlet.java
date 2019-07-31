package com.bs.xyl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class FileUploadServlet extends HttpServlet
{
	private static final long serialVersionUID = 2598654810371467006L;

	private String saveDirectoryName;

	private static final String address="D:\\workspace\\2016\\XYL\\WebRoot\\images\\upload\\";
	@Override
	public void init() throws ServletException
	{
		saveDirectoryName = getInitParameter("saveDirectoryName");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	        IOException
	{
		// �����ַ�����
		request.setCharacterEncoding("UTF-8");

		// ����'DiskFileItemFactory'����
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

		// ����'ServletFileUpload'����
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

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
					System.out.println("���е���ͨ����: �ϴ��˵�����|�ļ��ȼ� (" + requestOfValue + "), ���е�����Ϊ'" + fileItem.getFieldName()
							+ "'");
					Class<?> type = null;
					// ��ñ��е���ͨ����������������Ӧ������
					Method method = null;
					String methodName = Change.getMethodName(fileItem.getFieldName());
					if(methodName.equals("setState")||methodName.equals("setClass_id")||methodName.equals("setId")){
						type = Integer.class;
						method=objectClass.getMethod(methodName, type);
						method.invoke(stu, Integer.valueOf(requestOfValue));
					}else{
						type = String.class;
						method=objectClass.getMethod(methodName, type);
						method.invoke(stu, requestOfValue);
					}
				}
				else {
					// ����ϴ��ļ������� (�ļ��ϴ�����, �ϴ��ļ�������, ע��: �������ѱ���ȡ, �������ļ�������·��)
					String fileName = fileItem.getName();
					// �û�ѡ�����ϴ����ļ�, ���Ҹ��ļ������ֲ�Ϊ��
					if (fileItem.getName() != null && !"".equals(fileItem.getName())) {
						// ����ϴ��ļ�������
						String contentType = fileItem.getContentType();

						// ����ϴ��ļ��Ĵ�С
						long fileSize = fileItem.getSize();

						// ��ӡHttp������Ϣ ('Request') ���ϴ��ļ�����Ϣ
						System.out.println("�ϴ��ļ���" + fileName);
						System.out.println("�ϴ��ļ�����" + contentType);
						System.out.println("�ϴ��ļ���С" + fileSize);

						// �����Ŀ�ĸ�Ŀ¼
						String rootDirProject = getServletContext().getRealPath("/")+"images\\upload\\";
						// ƴ�ӱ�����ϴ��ļ���Ŀ¼
						File saveFileUploadFile = new File(rootDirProject);
						File saveFileUploadFile2 = new File(address);
						// ��������ϴ��ļ���Ŀ¼������, ����
						if (!saveFileUploadFile.exists()) {
							saveFileUploadFile.mkdirs();
						}
						if (!saveFileUploadFile2.exists()) {
							saveFileUploadFile2.mkdirs();
						}
						SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
						String newName = df.format(new Date());
						SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time = df2.format(new Date());
						// ƴ�ӱ����ϴ����ݵ��ļ�
						String str = fileName.substring(fileName.lastIndexOf("."));
						newName = newName+str;
						File saveTheFileUpload = new File(rootDirProject, newName);
						// �����ϴ����ļ�
						fileItem.write(saveTheFileUpload);
						System.out.println("??:"+saveTheFileUpload);
						File   file=new   File(saveFileUploadFile+"\\"+fileName);
						file.renameTo(new  File(newName));
						//�ŵ�����
						int bytesum = 0;
						int byteread = 0;
						InputStream inStream = new FileInputStream(saveFileUploadFile+"\\"+newName); 
						FileOutputStream fs = new FileOutputStream(address+newName);
						byte[] buffer = new byte[1444];
						while ( (byteread = inStream.read(buffer)) != -1) {
							bytesum += byteread; //�ֽ��� �ļ���С
							fs.write(buffer, 0, byteread);
						}
						inStream.close();
						stu.setImg(newName);
						request.setAttribute("uploadTips", "�ļ��ϴ��ɹ�");
					}
				}
			}
			StuDao dao = new StuDao();
			int num = dao.addNew(stu);
			if(num==1){
				response.sendRedirect("AdminViewStuServlet");
			}
			System.out.println("num:"+num);
		}
		catch (FileUploadException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
