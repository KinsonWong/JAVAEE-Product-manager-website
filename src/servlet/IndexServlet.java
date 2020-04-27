package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class IndexServlet
 */

@WebServlet("/IndexServlet")
//1.创建IndexServlet显示网站的首界面 
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //2.response解决乱码问题
        response.setContentType("text/html;charset=utf-8");
        //3.创建Session对象保存用户信息
        HttpSession session=request.getSession();
        //4.方法体中的参数要与setAttribute()键一致
        User user=(User) session.getAttribute("user");
        if(user==null){
            response.getWriter().print("您还未登陆，请<a href='login.jsp'>登陆</a>");
        }else{
            response.getWriter().println("您已登陆，欢迎您 "+user.getUsername());
            response.getWriter().println("<a href='LogoutServlet'>   退出登陆   </a>");
            response.getWriter().println("<a href='index.jsp'>   进入企业管理系统   </a>");
            

            //5.创建Cookie对象用来保存Session的id
            Cookie cookie=new Cookie("SESSIONID",session.getId());
            cookie.setMaxAge(30*60);
            cookie.setPath("/ProductManager");
            response.addCookie(cookie);
        }


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
 



