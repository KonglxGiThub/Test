package com.neusoft.services;

import java.sql.*;
import java.util.*;
import com.neusoft.system.db.DBUtils;


public final class A1010Services 
{
	
	/**
	 * �����޸�
	 * ����
	 * ����
	 * @param val={aab002,aab003,aab004,aab005,aab007,aab001};
	 * @return
	 * @throws Exception
	 */
	public boolean modify(String...val)throws Exception
	{
		Connection conn=null;
		PreparedStatement pstm1=null;
		PreparedStatement pstm2=null;
		PreparedStatement pstm3=null;
		PreparedStatement pstm4=null;
		try
		{
			conn=DBUtils.getConnection();
			StringBuilder sql1=new StringBuilder()
			.append("UPDATE AB01 A")
			.append("   SET A.AAB002=?,A.AAB003=?,A.AAB004=?,A.AAB005=TO_DATE(?,'YYYY-MM-DD'),")
			.append("       A.AAB007=?")
			.append(" WHERE A.AAB001=?")
			;
			pstm1=conn.prepareStatement(sql1.toString());
			pstm1.setObject(1, val[0]);
			pstm1.setObject(2, val[1]);
			pstm1.setObject(3, val[2]);
			pstm1.setObject(4, val[3]);
			pstm1.setObject(5, val[4]);
			pstm1.setObject(6, val[5]);
			
			double aab003=Double.parseDouble(val[1]);
			double aab202=aab003*0.4;
			double aab302=aab003*0.3;
			double aab402=aab003-aab202-aab302;
			
			String sql2="UPDATE AB02 SET AAB202=? WHERE AAB001=?";
			pstm2=conn.prepareStatement(sql2);
			pstm2.setObject(1, aab202);
			pstm2.setObject(2, val[5]);
			
			String sql3="UPDATE AB03 SET AAB302=? WHERE AAB001=?";
			pstm3=conn.prepareStatement(sql3);
			pstm3.setObject(1, aab302);
			pstm3.setObject(2, val[5]);
			
			String sql4="UPDATE AB04 SET AAB402=? WHERE AAB001=?";
			pstm4=conn.prepareStatement(sql4);
			pstm4.setObject(1, aab402);
			pstm4.setObject(2, val[5]);
			
			boolean tag=false;
			conn.setAutoCommit(false);
			try
			{
				pstm1.executeUpdate();
				pstm2.executeUpdate();
				pstm3.executeUpdate();
				pstm4.executeUpdate();
				
			    conn.commit();
			    tag=true;
			}
			catch(Exception ex)
			{
				conn.rollback();
				ex.printStackTrace();
			}
			finally
			{
				conn.setAutoCommit(true);
			}
			return tag;
		}
		finally
		{
			DBUtils.close(pstm1);
			DBUtils.close(pstm2);
			DBUtils.close(pstm3);
			DBUtils.close(pstm4);
			DBUtils.close(conn);
		}
	}

	
	/**
	 * ��һʵ����ѯ
	 * <
	 *   ����������ѯ��Ӧ������
	 * >
	 * @param aab001
	 * @return
	 * @throws Exception
	 */
	public Map findById(String aab001)throws Exception
	{
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			StringBuilder sql=new StringBuilder()
			.append("SELECT A.AAB002,A.AAB003,A.AAB004,TO_CHAR(A.AAB005,'YYYY-MM-DD') AAB005,")
			.append("       A.AAB007")
			.append("  FROM AB01 A")
			.append(" WHERE A.AAB001=?")
			;
			conn=DBUtils.getConnection();
			pstm=conn.prepareStatement(sql.toString());
			pstm.setObject(1, aab001);
			rs=pstm.executeQuery();
			
			Map ins=null;
			if(rs.next())
			{
				ins=new HashMap();
				ResultSetMetaData rsmd=rs.getMetaData();
				int count=rsmd.getColumnCount();
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
			}
		    return ins;     
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
			DBUtils.close(conn);
		}
	}
	
	/**
	 * ����ɾ��
	 * @param aab001   --  ��������
	 * @return
	 * @throws Exception
	 */
	public boolean delete(String aab001)throws Exception
	{
		Connection conn=null;
		PreparedStatement pstm1=null;   //ɾ����������(AB02)
		PreparedStatement pstm2=null;   //ɾ�����������(AB03)
		PreparedStatement pstm3=null;   //ɾ�����ѻ����(AB04)
		PreparedStatement pstm4=null;   //ɾ�����˱�(AB01)
		try
		{
			conn=DBUtils.getConnection();
			String sql1="DELETE FROM AB02 WHERE AAB001=?";
			pstm1=conn.prepareStatement(sql1);
			pstm1.setObject(1, aab001);
			
			String sql2="DELETE FROM AB03 WHERE AAB001=?";
			pstm2=conn.prepareStatement(sql2);
			pstm2.setObject(1,aab001);
			
			String sql3="DELETE FROM AB04 WHERE AAB001=?";
			pstm3=conn.prepareStatement(sql3);
			pstm3.setObject(1, aab001);
			
			String sql4="DELETE FROM AB01 WHERE AAB001=?";
			pstm4=conn.prepareStatement(sql4);
			pstm4.setObject(1, aab001);
			
			//�������񷵻�ֵ����
			boolean tag=false;
			//��������
			conn.setAutoCommit(false);
			try
			{
				//ִ�и������
				pstm1.executeUpdate();
				pstm2.executeUpdate();
				pstm3.executeUpdate();
				pstm4.executeUpdate();
				//�ύ����
				conn.commit();
				//�޸����񷵻�ֵ
				tag=true;
				
			}
			catch(Exception ex)
			{
				//����ع�
				conn.rollback();
				ex.printStackTrace();
			}
			finally
			{
				//��������
				conn.setAutoCommit(true);
			}
			return tag;
		}
		finally
		{
			DBUtils.close(pstm1);
			DBUtils.close(pstm2);
			DBUtils.close(pstm3);
			DBUtils.close(pstm4);
			DBUtils.close(conn);
		}
	}
	
	/**
	 * ����������ѯ
	 * @param val ={aab004,aab002,baab003,eaab003,baab005,eaab005};
	 * @return
	 * @throws Exception
	 */
	public List<Map> query(String...val)throws Exception
	{
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			String aab004=val[0];
			String aab002=val[1];
			String baab003=val[2];
			String eaab003=val[3];
			String baab005=val[4];
			String eaab005=val[5];
			
			
			conn=DBUtils.getConnection();
			StringBuilder sql=new StringBuilder()
			.append("SELECT E.AAB001,E.AAB002,E.AAB003,TO_CHAR(E.AAB005,'YYYY-MM-DD') AAB005,")
			.append("       A.FVALUE CNAAB004, B.AAB202,C.AAB302,D.AAB402 ")
			.append("  FROM SYSCODE A,AB02 B,AB03 C,AB04 D,AB01 E")
			.append(" WHERE E.AAB004=A.FCODE AND A.FNAME='AAB004'")
			.append("   AND B.AAB001=E.AAB001")
			.append("   AND C.AAB001=E.AAB001")
			.append("   AND D.AAB001=E.AAB001")
			;
			
			List pars=new ArrayList();
			if(aab004!=null && !aab004.equals(""))
			{
				sql.append(" AND E.AAB004=?");
				pars.add(aab004);
			}
			if(aab002!=null && !aab002.equals(""))
			{
				sql.append(" AND E.AAB002 LIKE ?");
				pars.add("%"+aab002+"%");
			}
			if(baab003!=null && !baab003.equals(""))
			{
				sql.append(" AND E.AAB003>=?");
				pars.add(baab003);
			}
		    if(eaab003!=null && !eaab003.equals(""))
		    {
		    	sql.append(" AND E.AAB003<=?");
		    	pars.add(eaab003);
		    }
		    if(baab005!=null && !baab005.equals(""))
		    {
		    	sql.append(" AND E.AAB005>=TO_DATE(?,'YYYY-MM-DD')");
		    	pars.add(baab005);
		    }
		    if(eaab005!=null && !eaab005.equals(""))
		    {
		    	sql.append(" AND E.AAB005<=TO_DATE(?,'YYYY-MM-DD')");
		    	pars.add(eaab005);
		    }
			sql.append(" ORDER BY E.AAB001 DESC");
			System.out.println(sql.toString());
			System.out.println(pars);
			pstm=conn.prepareStatement(sql.toString());	
			int size=pars.size();
			for(int i=0;i<size;i++)
			{
				pstm.setObject(i+1, pars.get(i));
			}
			
			rs=pstm.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int count=rsmd.getColumnCount();
			
			List<Map> rows=new ArrayList<Map>();
		    Map ins=null;
		    while(rs.next())
		    {
		    	ins=new HashMap();
		    	for(int i=1;i<=count;i++)
		    	{
		    		ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
		    	}
		    	rows.add(ins);
		    }
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
	 * <
	 *   ���������
	 *   ����¼���˳��:���������,����Ӵӱ�
	 * >
	 * @param val={aab002,aab003,aab004,aab005,aab007};
	 *            {"���¹���","5000","1","2015-10-27",null}
	 *              0       1      2      3      4
	 * @return
	 * @throws Exception
	 */
    public boolean add(String...val)throws Exception
    {
    	Connection conn=null;            //���Ӷ���
    	PreparedStatement pstm1=null;    //��ѯ��������
    	PreparedStatement pstm2=null;    //¼��AB01
    	PreparedStatement pstm3=null;    //¼��AB02
    	PreparedStatement pstm4=null;    //¼��AB03
    	PreparedStatement pstm5=null;    //¼��AB04
    	ResultSet rs=null;               //��ȡ�������к�
    	try
    	{
    		conn=DBUtils.getConnection();
    		//1.��ȡ��������
    		String sql1="SELECT S_AB01.NEXTVAL FROM DUAL";
    		pstm1=conn.prepareStatement(sql1);
    		rs=pstm1.executeQuery();
    		rs.next();
    		int aab001=rs.getInt(1);  //��ab01������,�ڴӱ������
    		
    		//2.�����������
    		StringBuilder sql2=new StringBuilder()
    		.append("INSERT INTO AB01(AAB001,AAB002,AAB003,AAB004,AAB005,AAB006,AAB007)")
    		.append("          VALUES(?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),SYSDATE,?)")
    		;
    		pstm2=conn.prepareStatement(sql2.toString());
    		pstm2.setObject(1, aab001);
    		pstm2.setObject(2, val[0]);
    		pstm2.setObject(3, val[1]);
    		pstm2.setObject(4, val[2]);
    		pstm2.setObject(5, val[3]);
    		pstm2.setObject(6, val[4]);
    		
    		//3.��������
    		//3.1��������,��ȡ�����ܽ���ת����double����
    		double aab003=Double.parseDouble(val[1]);   //���˽��
    		//3.2������Ӧ�ӱ�����
    		double aab202=aab003*0.4;                   //������    
    		double aab302=aab003*0.3;                   //��������
    		double aab402=aab003-aab202-aab302;         //���ѻ���
    		
    		//4.¼��ӱ�����������
    		StringBuilder sql3=new StringBuilder()
    		.append("INSERT INTO AB02(AAB201,AAB001,AAB202)")
    		.append("         VALUES(S_AB02.NEXTVAL,?,?)")
    		;
    		pstm3=conn.prepareStatement(sql3.toString());
    		pstm3.setObject(1, aab001);
    		pstm3.setObject(2, aab202);
    		
    		//5.¼���������
    		StringBuilder sql4=new StringBuilder()
    		.append("INSERT INTO AB03(AAB301,AAB001,AAB302)")
    		.append("          VALUES(S_AB03.NEXTVAL,?,?)")
    		;
    		pstm4=conn.prepareStatement(sql4.toString());
    		pstm4.setObject(1, aab001);
    		pstm4.setObject(2, aab302);
    		
    		//6.¼�����ѻ���
    		StringBuilder sql5=new StringBuilder()
    		.append("INSERT INTO AB04(AAB401,AAB001,AAB402)")
    		.append("          VALUES(S_AB04.NEXTVAL,?,?)")
    		;
    		pstm5=conn.prepareStatement(sql5.toString());
    		pstm5.setObject(1, aab001);
    		pstm5.setObject(2, aab402);
    		
    		//7.������ʽִ�����еĸ�����SQL���
    		boolean tag=false;
    		conn.setAutoCommit(false);
    		try
    		{
    			pstm2.executeUpdate();
    			pstm3.executeUpdate();
    			pstm4.executeUpdate();
    			pstm5.executeUpdate();
    			
    			conn.commit();
    			tag=true;
    		}
    		catch(Exception ex)
    		{
    			conn.rollback();
    			ex.printStackTrace();
    		}
    		finally
    		{
    			conn.setAutoCommit(true);
    		}
    		return tag;
    	}
    	finally
    	{
    		DBUtils.close(rs);
    		DBUtils.close(pstm1);
    		DBUtils.close(pstm2);
    		DBUtils.close(pstm3);
    		DBUtils.close(pstm4);
    		DBUtils.close(pstm5);
    		DBUtils.close(conn);
    	}
    }
}
