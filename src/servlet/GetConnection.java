package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {

	Connection conn = null;
	         
	         public Connection getConnection() throws ClassNotFoundException {
	             String driver="com.mysql.jdbc.Driver";                //����·��
	             String url="jdbc:mysql://localhost:3306/Product";    //���ݿ��ַ
	             String user="root";                                    //�������ݿ���û���
	             String password="root";                            //�û�����    
	             
	             Class.forName(driver);
	             try {
	                 conn = DriverManager.getConnection(url,user,password);
	             } catch (SQLException e) {
	                 // TODO Auto-generated catch block
	                 e.printStackTrace();
	             }
	             //����Connection����
	             return conn;
	         }
}
