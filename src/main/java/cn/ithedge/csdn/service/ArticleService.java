package cn.ithedge.csdn.service;

import cn.ithedge.csdn.domain.Article;
import cn.ithedge.csdn.domain.Page;

import java.util.List;

/**
 * @description: 文章管理接口
 * @author: hedgeway
 * @CreateTime: 2022/10/20 12:57
 * @param: null
 * @return: null
 */
public interface ArticleService {
    List<Article> findAll();

    /**
     * @description:
     * @author: hedgeway
     * @CreateTime: 2022/10/20 17:35
     * @param: articleid
     */
    void delSelectedArticle(String[] articleid);

    /**
     * @description:
     * @author: hedgeway
     * @CreateTime: 2022/10/20 17:35
     * @param: articleid
     */
    void delSelectedArticle(String articleid);

    void add(Article article);
    void updateArticle(Article article);
    Article findArticleById(String articleId);

    Page<Article> findArticleByPage(String _currentPage, String _rows);
}