package cn.ithedge.csdn.web.servlet;

import cn.ithedge.csdn.domain.Article;
import cn.ithedge.csdn.domain.Page;
import cn.ithedge.csdn.service.ArticleService;
import cn.ithedge.csdn.service.impl.ArticleServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Class: ArticleListServlet
 * @Package: cn.ithedge.csdn.web.servlet
 * @Author: hedgeway
 * @CreateTime: 2022/10/20 11:07
 */
@WebServlet("/articleListServlet/*")
public class ArticleListServlet extends BaseServlet{
    ArticleService articleService = new ArticleServiceImpl();

    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Article> articles = articleService.findAll();
        request.setAttribute("articles",articles);
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    //用来回显内容
    public void findOneArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.获取id
        String articleId = request.getParameter("articleId");
        //2.调用Service查询
        Article article = articleService.findArticleById(articleId);

        //3.将user存入request
        request.setAttribute("article",article);
        //4.转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }


    public void findArticleByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        //1.获取参数
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数

        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }


        if(rows == null || "".equals(rows)){
            rows = "10";
        }

        //2.调用service查询
        Page<Article> pb = articleService.findArticleByPage(currentPage,rows);
//        System.out.println(pb);

        //3.将PageBean存入request
        request.setAttribute("pb",pb);
        //4.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String[] ids = request.getParameterValues("articleId");
        //2.调用service删除
        articleService.delSelectedArticle(ids);

        //3.跳转查询所有Servlet
        response.sendRedirect(request.getContextPath()+"/articleListServlet/search");
    }


    //用来修改文章
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取map
        Map<String, String[]> map = request.getParameterMap();
        //3.封装对象
        Article article = new Article();
        try {
            BeanUtils.populate(article,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.调用Service修改
        articleService.updateArticle(article);

        //5.跳转到查询所有Servlet
        response.sendRedirect("/list.jsp");
//        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> map = request.getParameterMap();
        Article article = new Article();

        try {
            BeanUtils.populate(article,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        articleService.add(article);
        response.sendRedirect(request.getContextPath()+"/articleListServlet/search");
    }

}
