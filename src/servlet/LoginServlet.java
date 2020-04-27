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
		          //获取登录页面提交的数据
		          String loginName = request.getParameter("name");
		          String loginPassword = request.getParameter("password");
		          //sql语句
		          String selectUsername = "select name from usert";
		          String selectPassword = "select password from usert where name = ?";
		          
		          try {
		              //获取与数据库的链接
		              conn = new GetConnection().getConnection();
		              //遍历tb_user表，将数据库中所有username存入集合中
		              st = conn.createStatement();
		              rs = st.executeQuery(selectUsername);
		              List<String> usernameList = new ArrayList<String>();
		              while (rs.next()) {
		                  usernameList.add(rs.getString(1));
		              }
		              //关闭连接
		              if (rs != null) {
		                  rs.close();
		              }
		              if (st != null) {
		                  st.close();
		              }
		              //判断集合中是否存在所要登录的username
		              if (usernameList.contains(loginName)) {
		                  //查找username对应的password
		                  List<String> passwordList = new ArrayList<String>();
		                  ptst = (PreparedStatement) conn.prepareStatement(selectPassword);
		                  //设置ptst参数
		                  ptst.setString(1, loginName);
		                  rs = ptst.executeQuery();
		                  while (rs.next()) {
		                      passwordList.add(rs.getString(1));
		                  }
		                  //判断数据库与登录页面提交的password是否一致
		                  if (passwordList.get(0).equals(loginPassword)) {
		                	  User user=new User();
		                      user.setUsername(loginName);
		                      user.setPassword(loginPassword);
		                      request.getSession().setAttribute("user", user);
		                	  out.println("登陆成功, ");
		                      out.println("欢迎您,"+loginName+"。");
		                      response.sendRedirect("IndexServlet");
		                  } else {
		                      out.println("密码错误，请重新尝试。");
		                  }
		                  if (rs != null) {
		                      rs.close();
		                  }
		                  if (ptst != null) {
		                      ptst.close();
		                  }
		                  
		              } else {
		                  out.println("用户名不存在，请重试...");
		              }
		          
		          } catch (ClassNotFoundException e) {
		              // TODO Auto-generated catch block
		              e.printStackTrace();
		          } catch (SQLException e) {
		              // TODO Auto-generated catch block
		              e.printStackTrace();
		          } finally {
		              //关闭链接
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
