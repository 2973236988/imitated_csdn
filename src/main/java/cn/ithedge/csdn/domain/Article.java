package cn.ithedge.csdn.domain;

/**
 * @Description:
 * @Class: Article
 * @Package: cn.ithedge.csdn.domain
 * @Author: hedgeway
 * @CreateTime: 2022/10/20 10:55
 */
public class Article {

    private int articleId;
    //文章的作者，即用户名
    private String author;

    //文章的发表时间
    private String publicedTime;

    //文章标题
    private String articleTitle;

    //文件的内容
    private String articleContent;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicedTime() {
        return publicedTime;
    }

    public void setPublicedTime(String publicedTime) {
        this.publicedTime = publicedTime;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }


    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", author='" + author + '\'' +
                ", publicedTime='" + publicedTime + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContent='" + articleContent + '\'' +
                '}';
    }
}
