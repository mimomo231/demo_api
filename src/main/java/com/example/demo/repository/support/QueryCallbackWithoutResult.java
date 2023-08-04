package com.example.demo.repository.support;

import com.example.demo.repository.support.QueryCallBack;

import javax.persistence.EntityManager;

public abstract class QueryCallbackWithoutResult<T> implements QueryCallBack<T> {
    @Override
    public T doWithEntityManager(EntityManager entityManager) {
        doWithEntityManagerWithoutResult(entityManager);
        return null;
    }
    //This is convenience implementation to help you execute queries that don’t return rows or scalar values.
    protected abstract void doWithEntityManagerWithoutResult(EntityManager entityManager);
}
