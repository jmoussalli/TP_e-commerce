package com.ecommerce.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class GenericDAO<T> {
    private Class<T> entityClass;

    private EntityManager entityManager = EntityManagerSingleton.getEntityManager("tp_ecommerce");
    private EntityTransaction tx = entityManager.getTransaction();

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T save(T entity) {
        tx.begin();
        entityManager.persist(entity);
        tx.commit();
        return entity;
    }

    public T findById(Integer id) {
        return entityManager.find(entityClass, id);
    }

    public T update(T entityToUpdate) {
        tx.begin();
        entityManager.merge(entityToUpdate);
        tx.commit();
        return entityToUpdate;
    }

    public T delete(T entityToDelete) {
        tx.begin();
        entityManager.remove(entityToDelete);
        tx.commit();
        return entityToDelete;
    }

    public List<T> findAll() {
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
        return query.getResultList();
    }
}