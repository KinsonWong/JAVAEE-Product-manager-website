package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import page.PageBean;
import product.Product;
import service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*ProductServlet��Ϊ��ʵ��ҳ��������
��ͨ�Ŷ���Ƶģ�ʵ����ProductService
�ж���Ľӿ�
*/


public class ProductServlet extends BaseServlet{
	/*�ڿ������У������ķ���ֵ��
    ��תҳ���JSPҳ�湲ͬ����
  */
    private ProductService productService = new ProductService();

    public String add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        request.setCharacterEncoding("utf-8");
      //�����������Product����Mapping
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);
        product.setId(CommonUtils.uuid());      //�������ID
        productService.add(product);

      //�趨��ӳɹ�֮�����Ϣ
        request.setAttribute("message", "�����Ʒ�ɹ���");
        return "/message.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{

    	//�õ�Ҫɾ������Ʒ��ID
        String id = request.getParameter("id");
        productService.delete(id);

        //�趨ɾ���ɹ�֮�����Ϣ
        request.setAttribute("message", "ɾ����Ʒ�ɹ���");
        return "/message.jsp";
    }

    /*edit������Ҫ������������ʾ��Ʒ�ѱ������Ϣ�Լ��༭��Ʒ��Ϣ��
    ��ʾ��Ʒ��Ϣ������ΪpreEdit,��ʾ��Ʒ�ѱ������Ϣ��
    �༭������Ϊedit���༭��Ʒ��Ϣ������.
  */
    public String preEdit(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
    	 //�õ�Ҫ�༭����Ʒ��ID,��ѯ������Ʒ����Ϣ
        String id = request.getParameter("id");
        Product product = productService.find(id);      //��ѯ����Ʒ����Ϣ

      //�趨��ѯ����Ʒ��Ϣ֮��Ĳ���
        request.setAttribute("product", product);
        return "/edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
    	 /*������add�������ƣ����õ����������
        ��product����mapping�����и���
       */
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);
        productService.edit(product);

        //�趨�༭�ɹ�֮�����Ϣ
        request.setAttribute("message", "�༭��Ʒ�ɹ���");
        return "/message.jsp";
    }

    //�ڱ�дshowAll����֮ǰ����Ҫ�ȱ�дgetPageCode��getURL����
    private int getPageCode(HttpServletRequest request){
    	//�õ�����ҳ��Ĳ��������û�У�������Ϊ1
        String value = request.getParameter("pageCode");
        if(value == null || value.trim().isEmpty()){
            return 1;
        }
        return Integer.parseInt(value);
    }

    //ֻ��Ҫ�õ���������ҳ��(PageCode)֮ǰ������
    private String getUrl(HttpServletRequest request){
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String queryString = request.getQueryString();

      //queryStringΪ��ѯ�ַ���
        if (queryString.contains("&pageCode=")) {
            int index = queryString.lastIndexOf("&pageCode=");
            queryString = queryString.substring(0, index);
        }
        return contextPath + servletPath + "?" + queryString;
    }

    //��ʾ��ǰ��������Ʒ��Ϣ
    public String showAll(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        request.setCharacterEncoding("utf-8");
        int pageCode = getPageCode(request);
        int pageRecord = 10;         //ÿҳ10���¼

        PageBean<Product> pageBean = productService.showAll(pageCode, pageRecord);
      //�õ������url
        pageBean.setUrl(getUrl(request));

      //����ҳ������
        request.setAttribute("pageBean",pageBean);
        return "/content.jsp";
    }

    
    public String query(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
      //�õ��������������mapping
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);

        int pageCode = getPageCode(request);
        //ÿҳ10���¼
        int pageRecord = 10;

        PageBean<Product> pageBean = productService.query(product, pageCode, pageRecord);

        pageBean.setUrl(getUrl(request));

      //�趨ҳ������
        request.setAttribute("pageBean",pageBean);
        return "/content.jsp";
    }
}
