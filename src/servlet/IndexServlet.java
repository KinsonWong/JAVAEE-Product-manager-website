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
//1.����IndexServlet��ʾ��վ���׽��� 
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //2.response�����������
        response.setContentType("text/html;charset=utf-8");
        //3.����Session���󱣴��û���Ϣ
        HttpSession session=request.getSession();
        //4.�������еĲ���Ҫ��setAttribute()��һ��
        User user=(User) session.getAttribute("user");
        if(user==null){
            response.getWriter().print("����δ��½����<a href='login.jsp'>��½</a>");
        }else{
            response.getWriter().println("���ѵ�½����ӭ�� "+user.getUsername());
            response.getWriter().println("<a href='LogoutServlet'>   �˳���½   </a>");
            response.getWriter().println("<a href='index.jsp'>   ������ҵ����ϵͳ   </a>");
            

            //5.����Cookie������������Session��id
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
 



