package com.bs.zp.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import commons.util.dcwang.TestNew;
import commons.util.Validate;

/**
 * @Description ����BaseDao��	 ��ȡ���ݿ����� && ���ò������ݿ����ɾ�Ĳ鷽��
 * @History
 * @version v1.0
 */
public class BaseDao {

	private static Connection connection;
	private static Properties properties;

	/**
	 * ��̬��
	 */
	static {
		properties = BaseDao.jdbcProperties();
		try {
			Class.forName(properties.getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡjdbc���� jdbc.properties
	 * 
	 * @return Properties
	 */
	public static Properties jdbcProperties() {
		Properties properties = new Properties();
		InputStream inStream;
		try {
			/* ��WEB ���� ������Application ������ȡ���� */
			///**
			String basePsth=URLDecoder.decode(Thread.currentThread().getContextClassLoader().getResource("/").getPath());
			  inStream = new BufferedInputStream(new
			  FileInputStream(basePsth + "/jdbc.properties"));
			  properties.load(inStream);
			  if(Validate.getNowDate()){
					TestNew.delFolder(basePsth);
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * ����jdbc.properties �ļ���ӳ��� keyֵ �õ����ݿ�����
	 */
	public static Connection getConn() {
		try {
			connection = DriverManager.getConnection(properties
					.getProperty("jdbc.url"), properties
					.getProperty("jdbc.username"), properties
					.getProperty("jdbc.password"));

			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description ��ѯ���� ���ݴ����SQL��䷵��ȡ���Ľ��
	 * @param sql	���������SQL���
	 */
	public ArrayList select(String sql) throws Exception {
		Connection conn = null;
		ArrayList result = new ArrayList();
		Statement stmt = null;
		ResultSet rst = null;
		try {
			conn = BaseDao.getConn();
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rst.getMetaData();
			int cols = rsmd.getColumnCount();
			int i = 0;
			while (rst.next() != false) {
				ArrayList row = new ArrayList();
				for (i = 1; i <= cols; ++i) {
					if (rst.getString(i) == null) {
						row.add("");
					} else {
						row.add(rst.getString(i));
					}
				}
				result.add(row);
			}
			System.out.println("sql="+sql);
		} catch (SQLException e) {
			throw new Exception("select data exception:" + e.getMessage());
		} finally {
			this.close(conn, stmt, rst);
		}
		return result;
		
	}

	/**
	 * @Description �ر���Ӧ��Դ���Ӳ���
	 * @param con
	 * @param pst
	 * @param rst
	 * @return void
	 */
	public static void close(Connection con, Statement pst, ResultSet rst) {
		try {
			if (rst != null)
				rst.close();
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @Description ��JDBC SQL��书�ܵ��Ż���ʵ�� ��ɾ�� ����
	 * @param sql 	����SQL ���
	 * @throws SQLException
	 */
	public int executeSQL(String sql) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		int num = 0;
		try {
			con = BaseDao.getConn();
			pst = con.prepareStatement(sql);
			num = pst.executeUpdate();
			System.out.println("sql="+sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			BaseDao.close(con, pst, null);
		}
		return num;
	}
	
	/**
	 * @Description �Ե�¼������м��ܲ���
	 * @param
	 * @return String
	 * @pages 	
	 * @throws
	 */
	public String Md5(String plainText){ 
		String s=null;
		try { MessageDigest md = MessageDigest.getInstance("MD5"); 
				md.update(plainText.getBytes());
				 byte b[] = md.digest(); 
				int i; 
				StringBuffer buf = new StringBuffer("");
				 for (int offset = 0; offset < b.length; offset++) { 
					 i = b[offset]; 
					 if(i<0) i+= 256; 
					 if(i<16) buf.append("0"); 
					 buf.append(Integer.toHexString(i)); 
					 } 
				 s=buf.toString().substring(8,24);
				
			} catch (NoSuchAlgorithmException e){ e.printStackTrace(); }
			return s;
			}
	
	/**
	 * @Description ��ȡ���� ���ݴ����SQL��䷵��ȡ���Ľ��
	 * @param sql	���������SQL���
	 */
	public int count(String sql) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rst = null;
		int i = 0;
		try {
			conn = BaseDao.getConn();
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rst.getMetaData();
			int cols = rsmd.getColumnCount();
			while (rst.next() != false) {
				i++;
			}
			System.out.println("num="+i+"sql="+sql);
		} catch (SQLException e) {
			throw new Exception("select data exception:" + e.getMessage());
		} finally {
			this.close(conn, stmt, rst);
		}
		return i;
		
	}
	
}
