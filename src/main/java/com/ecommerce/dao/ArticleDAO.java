package com.ecommerce.dao;

import com.ecommerce.entity.Article;
import jakarta.inject.Inject;

import java.util.List;

public class ArticleDAO {

    public static GenericDAO<Article> articleDAO = new GenericDAO<>(Article.class);

    public static Article save(Article article) {
        System.out.println("Article saved : " + article);
        return articleDAO.save(article);
    }

    public static Article update(Article article) {
        System.out.println("Article updated : " + article);
        return articleDAO.update(article);
    }

    public static List<Article> findAll() {
        List<Article> articles = articleDAO.findAll();
        System.out.println("All articles : " + articles);
        return articleDAO.findAll();
    }

    public static Article findById(Integer id) {
        Article article = articleDAO.findById(id);
        System.out.println("Article found : " + article);
        return articleDAO.findById(id);
    }

    public static Article delete(Integer id) {
        Article article = articleDAO.findById(id);
        System.out.println("Article deleted : " + article);
        return articleDAO.delete(article);
    }

}
