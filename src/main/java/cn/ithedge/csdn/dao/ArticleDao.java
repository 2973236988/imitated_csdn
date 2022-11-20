package cn.ithedge.csdn.dao;

import cn.ithedge.csdn.domain.Article;

import java.util.List;


public interface ArticleDao {
    List<Article> findAll();
    void delete(int articleId);
    void add(Article article);
    void update(Article article);
    Article findById(int articleId);
    int findTotalCount();
    List<Article> findByPage(int start, int rows);
}
