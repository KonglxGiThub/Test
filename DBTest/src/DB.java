

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DB {
	public static final String url = "jdbc:mysql://127.0.0.1/shopping";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123";
	public Connection conn = null;
	public PreparedStatement pst = null;
	
	public DB(String sql){
		try {
			Class.forName(name);
			
			conn = DriverManager.getConnection(url,user,password);
			pst = conn.prepareStatement(sql);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			this.conn.close();
			this.pst.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
