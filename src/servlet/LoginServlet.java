package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.GetConnection;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		          
		          Connection conn = null;
		          Statement st = null;
		          ResultSet rs = null;
		          PreparedStatement ptst = null;
		          //��ȡ��¼ҳ���ύ������
		          String loginName = request.getParameter("name");
		          String loginPassword = request.getParameter("password");
		          //sql���
		          String selectUsername = "select name from usert";
		          String selectPassword = "select password from usert where name = ?";
		          
		          try {
		              //��ȡ�����ݿ������
		              conn = new GetConnection().getConnection();
		              //����tb_user�������ݿ�������username���뼯����
		              st = conn.createStatement();
		              rs = st.executeQuery(selectUsername);
		              List<String> usernameList = new ArrayList<String>();
		              while (rs.next()) {
		                  usernameList.add(rs.getString(1));
		              }
		              //�ر�����
		              if (rs != null) {
		                  rs.close();
		              }
		              if (st != null) {
		                  st.close();
		              }
		              //�жϼ������Ƿ������Ҫ��¼��username
		              if (usernameList.contains(loginName)) {
		                  //����username��Ӧ��password
		                  List<String> passwordList = new ArrayList<String>();
		                  ptst = (PreparedStatement) conn.prepareStatement(selectPassword);
		                  //����ptst����
		                  ptst.setString(1, loginName);
		                  rs = ptst.executeQuery();
		                  while (rs.next()) {
		                      passwordList.add(rs.getString(1));
		                  }
		                  //�ж����ݿ����¼ҳ���ύ��password�Ƿ�һ��
		                  if (passwordList.get(0).equals(loginPassword)) {
		                	  User user=new User();
		                      user.setUsername(loginName);
		                      user.setPassword(loginPassword);
		                      request.getSession().setAttribute("user", user);
		                	  out.println("��½�ɹ�, ");
		                      out.println("��ӭ��,"+loginName+"��");
		                      response.sendRedirect("IndexServlet");
		                  } else {
		                      out.println("������������³��ԡ�");
		                  }
		                  if (rs != null) {
		                      rs.close();
		                  }
		                  if (ptst != null) {
		                      ptst.close();
		                  }
		                  
		              } else {
		                  out.println("�û��������ڣ�������...");
		              }
		          
		          } catch (ClassNotFoundException e) {
		              // TODO Auto-generated catch block
		              e.printStackTrace();
		          } catch (SQLException e) {
		              // TODO Auto-generated catch block
		              e.printStackTrace();
		          } finally {
		              //�ر�����
		              if (conn != null) {
		                  try {
		                      conn.close();
		                 } catch (SQLException e) {
		                     // TODO Auto-generated catch block
		                     e.printStackTrace();
		                 }
		             }
		         }
		         out.flush();
		         out.close();
	}

}
