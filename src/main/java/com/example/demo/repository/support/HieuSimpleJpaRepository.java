package com.example.demo.repository.support;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
public class HieuSimpleJpaRepository<E, ID extends Serializable> extends SimpleJpaRepository<E, ID>
        implements HieuJpaRepository {
    private final EntityManager entityManager;
    public HieuSimpleJpaRepository(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }
    @Override
    public <T> List<T> findAll(QueryCallBack<List<T>> callback) {
        return callback.doWithEntityManager(this.entityManager);
    }

    @Override
    public <T> T findOne(QueryCallBack<T> callback) {
        return null;

    }
}
