package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {

	Connection conn = null;
	         
	         public Connection getConnection() throws ClassNotFoundException {
	             String driver="com.mysql.jdbc.Driver";                //驱动路径
	             String url="jdbc:mysql://localhost:3306/Product";    //数据库地址
	             String user="root";                                    //访问数据库的用户名
	             String password="root";                            //用户密码    
	             
	             Class.forName(driver);
	             try {
	                 conn = DriverManager.getConnection(url,user,password);
	             } catch (SQLException e) {
	                 // TODO Auto-generated catch block
	                 e.printStackTrace();
	             }
	             //返回Connection对象
	             return conn;
	         }
}
