package com.ecommerce.dao;

import com.ecommerce.entity.Categorie;

import java.util.List;

public class CategorieDAO {

    public static GenericDAO<Categorie> categorieDAO = new GenericDAO<>(Categorie.class);

    public static Categorie save(Categorie categorie) {
        System.out.println("Categorie saved : " + categorie);
        return categorieDAO.save(categorie);
    }

    public static Categorie update(Categorie categorie) {
        System.out.println("Categorie updated : " + categorie);
        return categorieDAO.update(categorie);
    }

    public static List<Categorie> findAll() {
        List<Categorie> categories = categorieDAO.findAll();
        System.out.println("All categories : " + categories);
        return categorieDAO.findAll();
    }

    public static Categorie findById(Integer id) {
        Categorie categorie = categorieDAO.findById(id);
        System.out.println("Categorie found : " + categorie);
        return categorieDAO.findById(id);
    }

    public static Categorie delete(Integer id) {
        Categorie categorie = categorieDAO.findById(id);
        System.out.println("Categorie deleted : " + categorie);
        return categorieDAO.delete(categorie);
    }

}
