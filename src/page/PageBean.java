package page;

import java.util.List;

//pageBean<Object>����Ϊ�������ܹ���ҳ��ʾ����Ƶ�

public class PageBean<Object> {
    private int pageCode;           //ҳ��
    private int pageRecord;         //ҳ��ļ�¼��
    private int totalPage;          //��ҳ��
    private int totalRecord;        //�ܼ�¼
    private List<Object> beanList;  //��ǰҳ������
    private String url;

    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
    }
    public int getPageCode() {
        return pageCode;
    }

    public void setPageRecord(int pageRecord) {
        this.pageRecord = pageRecord;
    }
    public int getPageRecord() {
        return pageRecord;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    //getTotalPage��Ҫ���м��㣬����һҳ10����¼���ܹ�11����¼����Ҫ2ҳ
    public int getTotalPage(){
        totalPage = totalRecord/pageRecord;
        return totalRecord % pageRecord == 0 ? totalPage : totalPage + 1;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
    public int getTotalRecord() {
        return totalRecord;
    }

    public void setBeanList(List<Object> beanList) {
        this.beanList = beanList;
    }
    public List<Object> getBeanList() {
        return beanList;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
