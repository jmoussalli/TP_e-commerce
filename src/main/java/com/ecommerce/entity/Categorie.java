package com.ecommerce.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String nom;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Article> articles = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void addArticle(Article a){
        articles.add(a);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", articles=" + articles.toString() +
                '}';
    }
}
