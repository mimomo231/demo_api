package com.example.demo.repository.support;

import javax.persistence.EntityManager;
// this interface use for to access EntityManager
@FunctionalInterface
public interface QueryCallBack<T> {
    T doWithEntityManager(EntityManager entityManager);
}
