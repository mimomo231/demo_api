package com.example.demo.repository.support;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface HieuJpaRepository {
    <T> List<T> findAll(QueryCallBack<List<T>> callback);
    <T> T findOne(QueryCallBack<T> callback);
}
