package com.bs.xyl.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bs.xyl.util.BaseDao;

public class CommonDao extends BaseDao {
	String sql=null;
	int num=0;
	//admin�û���¼
	public String admLogin(String account){
		sql="select pwd from t_admin where account='"+account+"'  ";
		List list =null;
		try {
			list = super.select(sql);
			if (!list.isEmpty()) {
				ArrayList tempRow = (ArrayList) list.get(0);
				return (String) tempRow.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//student�û���¼
	public String stuLogin(String stu_no){
		//state=1��ͨ����֤���й���Աȷ��
		sql="select pwd from t_stu where stu_no='"+stu_no+"' and state=1 ";
		List list =null;
		try {
			list = super.select(sql);
			if (!list.isEmpty()) {
				ArrayList tempRow = (ArrayList) list.get(0);
				return (String) tempRow.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//���ݱ���������ɾ�����ݷ���
	public int delete(String ids,String table_name){
		if (ids.endsWith(",")) {
			int idCnt = 0;
			ids = ids.substring(0, ids.length() - 1);
			// ��ȡ�Զ��ŷָ���ַ���ת����int
			String[] idArray = ids.split(",");
			// �������鳤��ѭ��sql���
			for (int i = 0; i < idArray.length; i++) {
				if (idArray[i] instanceof String) {
					ids = idArray[i].replace("'", "");
					String sql = "delete from "+table_name+" where id = " + ids;
					try {
						idCnt += super.executeSQL(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					System.err.println("�����ǿյ�");
				}
			}
			System.out.println(idCnt);
			return idCnt;
		} else {
			System.out.println("ɾ������ʧ��");
			return 0;
		}
	}
}
