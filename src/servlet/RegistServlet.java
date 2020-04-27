package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import servlet.GetConnection;


/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		          response.setCharacterEncoding("UTF-8");
		          response.setContentType("text/html");
		         
		          PrintWriter out = response.getWriter();
		          
		          Statement st = null;
		          ResultSet rs  = null;
		          
		          PreparedStatement ptst = null;
		          //�����û�������
		          List<String> usernameList = new ArrayList<String>();
		          
		          //��ȡע���û���
		          String registName = request.getParameter("name");
		          //��ȡע���û�����
		         String registPassword = request.getParameter("password");
		         //��ȡע���û���������
		          String registRepeatpsd = request.getParameter("relpassword");
		          //��ȡע���û��ֻ�����
		          String phoneNumber = request.getParameter("phone");
		          //��ȡ�û�����
		          String email = request.getParameter("email");
		          
		          //��ȡ��MySQL���ӵ�Connection����
		          Connection conn = null;
		          try {
		              conn = new GetConnection().getConnection();
		              /**
		               * �ж����������Ƿ�һ�£�
		               *         �ǣ�����ע�᣻
		               *         �񣺷��ش���
		               */
		              if (registPassword.equals(registRepeatpsd)) {
		                  try {    
		                      /**
		                       * �ж��û������Ƿ��Ѿ����ڸ��û�
		                       * 1.����tb_user�������õ�username�ֶ�
		                       * 2.��username�ֶ��е��������ݴ��뼯���У�
		                       * 3.�жϼ����кͷ���ע����û���
		                       *         3.1������У����ص�errorҳ��
		                       *         3.2�����û�У�����ע�����
		                       */
		                      
		                      //����tb_user����username�ֶ�
		                      String select = "select name from usert";
		                      st = conn.createStatement();
		                      rs = st.executeQuery(select);
		                      //��username�ֶε��������ݴ��뼯����
		                      
		                      while (rs.next()) {
		                          usernameList.add(rs.getString(1));
		                      }
		                      //�ر�ResultSet��Statement����
		                      rs.close();
		                      st.close();
		                      
		                  } catch (SQLException e) {
		                     // TODO Auto-generated catch block
		                      e.printStackTrace();
		                  } 
		                  
		                  if (usernameList.contains(registName)) {
		                      out.println("�û�����ע�ᣬ�����³��ԡ�");
		                  } else {
		                      String insert = "insert into usert(name,password,phone,email) values(?,?,?,?)";
		                      try {
		                          ptst = conn.prepareStatement(insert);
		                         //����ptst����
		                         ptst.setString(1, registName);
		                         ptst.setString(2,registPassword);
		                         ptst.setString(4,phoneNumber);
		                         ptst.setString(3, email);
		                         //ִ��sql���
		                         ptst.execute();
		                        out.println("ע��ɹ���");
		                         //�رչر�ResultSet��Statement����
		                         ptst.close();
		                         
		                     } catch (SQLException e) {
		                         // TODO Auto-generated catch block
		                         e.printStackTrace();
		                     }
		                     
		                 }
		            } else {
		                 out.println("�����������벻һ�£������³��ԡ�");
		             }
		         } catch (ClassNotFoundException e1) {
		             // TODO Auto-generated catch block
		             e1.printStackTrace();
		         } finally {
		            try {
		                 //�ر�Connection����
		                 if (conn != null) {
		                     conn.close();
		                 }
		             } catch (SQLException e) {
		                 // TODO Auto-generated catch block
		                 e.printStackTrace();
		            }
		         }
		         
		         out.flush();
		         out.close();
	}

}
