package com.neusoft.services;

import java.sql.*;
import java.util.*;
import com.neusoft.system.db.DBUtils;

public final class LyBookServices 
{
	public boolean delete(String lid)throws Exception
	{
		Connection conn=null;
		PreparedStatement pstm=null;
		try
		{
			conn=DBUtils.getConnection();
			String sql="DELETE FROM LYBOOK WHERE LID=?";
			pstm=conn.prepareStatement(sql);
			pstm.setObject(1, lid);
			return pstm.executeUpdate()>0;
		}
		finally
		{
			DBUtils.close(pstm);
			DBUtils.close(conn);
		}
	}
	
	
	/**
	 * ���ݲ�ѯ����
	 * @return
	 * @throws Exception
	 */
	public List query()throws Exception
	{
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			conn=DBUtils.getConnection();
			StringBuilder sql=new StringBuilder()
			.append("SELECT A.LID,A.LYR,A.LYBT,A.LYNR,A.LYSJ")
			.append("  FROM LYBOOK A")
			.append(" ORDER BY A.LID DESC ")
			;
			pstm=conn.prepareStatement(sql.toString());
			rs=pstm.executeQuery();
			
			//1.����Listװ��ȫ������
			List rows=new ArrayList();
			//2.����װ�ص�ǰ�����ݵ�Map����
			Map ins=null;
			//3.ͨ��ѭ������rs�����
			while(rs.next())
			{
				//4.��װ�ص�ǰ�����ݵ�Map����,ʵ����
				ins=new HashMap();
				//5.���еı���,��ΪMap��key,���ж�Ӧ������ΪMap��value,��װ��ǰ��,���е���
				ins.put("LID", rs.getString(1));
				ins.put("LYR", rs.getString(2));
				ins.put("LYBT", rs.getString(3));
				ins.put("LYNR", rs.getString(4));
				ins.put("LYSJ", rs.getString(5));
				//6.��װ�ص�ǰ�����ݵ�map�������List
				rows.add(ins);
			}
			//7��List����
			return rows;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
			DBUtils.close(conn);
		}
	}
	
	/**
	 * �������
	 * @param val
	 * @return
	 * @throws Exception
	 */
   public boolean addLybook(String...val)throws Exception
   {
	   Connection conn=null;
	   PreparedStatement pstm=null;
	   try
	   {
		   conn=DBUtils.getConnection();
		   StringBuilder sql=new StringBuilder()
		   .append("INSERT INTO LYBOOK(LID,LYR,LYBT,LYNR,LYSJ)")
		   .append("        VALUES(S_LY.NEXTVAL,?,?,?,SYSDATE)")
		   ;
		   pstm=conn.prepareStatement(sql.toString());
		   int size=val.length;
		   for(int i=0;i<size;i++)
		   {
			   pstm.setObject(i+1, val[i]);
		   }
		   return pstm.executeUpdate()>0;
	   }
	   finally
	   {
		   DBUtils.close(pstm);
		   DBUtils.close(conn);
	   }
   }
}
