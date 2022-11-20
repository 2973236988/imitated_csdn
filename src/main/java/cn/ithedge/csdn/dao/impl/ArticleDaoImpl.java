package cn.ithedge.csdn.dao.impl;

import cn.ithedge.csdn.dao.ArticleDao;
import cn.ithedge.csdn.domain.Article;
import cn.ithedge.csdn.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description:
 * @Class: ArticleDaoImpl
 * @Package: cn.ithedge.csdn.dao.impl
 * @Author: hedgeway
 * @CreateTime: 2022/10/20 11:12
 */
public class ArticleDaoImpl implements ArticleDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Article> findAll() {
        //使用jdbc操作数据库
        String sql = "select * from article";
        List<Article> articles = template.query(sql, new BeanPropertyRowMapper<Article>(Article.class));

        return articles;
    }

    @Override
    public void delete(int articleId) {
        String sql = "delete from user where articleId = ?";
        template.update(sql, articleId);


    }

    @Override
    public void add(Article article) {
        String sql = "insert into article(articleTitle,articleContent,author) values(?,?,?)";
        template.update(sql,article.getArticleTitle(),article.getArticleContent(),article.getAuthor());
    }

    @Override
    public void update(Article article) {
        String sql = "update user set articleTitle = ?,articleContent = ? ,author = ? where id = ?";
        template.update(sql, article.getArticleTitle(), article.getArticleContent(),article.getAuthor(),article.getArticleId());
    }

    @Override
    public Article findById(int articleId) {
        String sql = "select * from article where articleId = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Article>(Article.class), articleId);
    }

    @Override
    public int findTotalCount(){
        //1.定义模板初始化sql
        String sql = "select count(*) from article";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public List<Article> findByPage(int start, int rows) {
        String sql = "select * from article where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<Object>();


        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
//        System.out.println(sql);
//        System.out.println(params);

        return template.query(sql,new BeanPropertyRowMapper<Article>(Article.class),params.toArray());
    }



}
