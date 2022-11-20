package cn.ithedge.csdn.service.impl;

import cn.ithedge.csdn.dao.ArticleDao;
import cn.ithedge.csdn.dao.impl.ArticleDaoImpl;
import cn.ithedge.csdn.domain.Article;
import cn.ithedge.csdn.domain.Page;
import cn.ithedge.csdn.service.ArticleService;


import java.util.List;

/**
 * @Description:
 * @Class: ArticleServiceImpl
 * @Package: cn.ithedge.csdn.service.impl
 * @Author: hedgeway
 * @CreateTime: 2022/10/20 11:09
 */
public class ArticleServiceImpl implements ArticleService {

    private ArticleDao dao = new ArticleDaoImpl();

    @Override
    public List<Article> findAll() {
        //调用dao查询
        return dao.findAll();
    }

    @Override
    public void add(Article article) {
        dao.add(article);
    }


    @Override
    public void delSelectedArticle(String articleid){
        dao.delete(Integer.parseInt(articleid));

    }


    @Override
    public void delSelectedArticle(String[] articleids){

        if (articleids != null && articleids.length >0){
            for (String articleid : articleids) {
                dao.delete(Integer.parseInt(articleid));
            }
        }
    }


    //前端交互时，把登录用户名字赋值给article的author
    @Override
    public void updateArticle(Article article) {
        dao.update(article);
    }

    //修改文章时，需要找到文章的id显示出来
    @Override
    public Article findArticleById(String articleId) {
        return dao.findById(Integer.parseInt(articleId));
    }

    @Override
    public Page<Article> findArticleByPage(String _currentPage, String _rows) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if(currentPage <=0) {
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        Page<Article> pb = new Page<Article>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount();
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<Article> list = dao.findByPage(start,rows);
        pb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

}
