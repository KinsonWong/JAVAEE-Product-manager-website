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
		          //创建用户名集合
		          List<String> usernameList = new ArrayList<String>();
		          
		          //获取注册用户名
		          String registName = request.getParameter("name");
		          //获取注册用户密码
		         String registPassword = request.getParameter("password");
		         //获取注册用户二次密码
		          String registRepeatpsd = request.getParameter("relpassword");
		          //获取注册用户手机号码
		          String phoneNumber = request.getParameter("phone");
		          //获取用户邮箱
		          String email = request.getParameter("email");
		          
		          //获取与MySQL链接的Connection对象
		          Connection conn = null;
		          try {
		              conn = new GetConnection().getConnection();
		              /**
		               * 判断两次密码是否一致：
		               *         是：继续注册；
		               *         否：返回错误；
		               */
		              if (registPassword.equals(registRepeatpsd)) {
		                  try {    
		                      /**
		                       * 判断用户表中是否已经存在该用户
		                       * 1.遍历tb_user表中所用的username字段
		                       * 2.将username字段中的所有数据存入集合中；
		                       * 3.判断集合中和否含有注册的用户名
		                       *         3.1：如果有，返回到error页面
		                       *         3.2：如果没有，进行注册操作
		                       */
		                      
		                      //遍历tb_user表中username字段
		                      String select = "select name from usert";
		                      st = conn.createStatement();
		                      rs = st.executeQuery(select);
		                      //将username字段的所有数据存入集合中
		                      
		                      while (rs.next()) {
		                          usernameList.add(rs.getString(1));
		                      }
		                      //关闭ResultSet和Statement链接
		                      rs.close();
		                      st.close();
		                      
		                  } catch (SQLException e) {
		                     // TODO Auto-generated catch block
		                      e.printStackTrace();
		                  } 
		                  
		                  if (usernameList.contains(registName)) {
		                      out.println("用户名已注册，请重新尝试。");
		                  } else {
		                      String insert = "insert into usert(name,password,phone,email) values(?,?,?,?)";
		                      try {
		                          ptst = conn.prepareStatement(insert);
		                         //设置ptst参数
		                         ptst.setString(1, registName);
		                         ptst.setString(2,registPassword);
		                         ptst.setString(4,phoneNumber);
		                         ptst.setString(3, email);
		                         //执行sql语句
		                         ptst.execute();
		                        out.println("注册成功。");
		                         //关闭关闭ResultSet和Statement链接
		                         ptst.close();
		                         
		                     } catch (SQLException e) {
		                         // TODO Auto-generated catch block
		                         e.printStackTrace();
		                     }
		                     
		                 }
		            } else {
		                 out.println("两次密码输入不一致，请重新尝试。");
		             }
		         } catch (ClassNotFoundException e1) {
		             // TODO Auto-generated catch block
		             e1.printStackTrace();
		         } finally {
		            try {
		                 //关闭Connection链接
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
