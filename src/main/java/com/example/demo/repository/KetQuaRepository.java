package com.example.demo.repository;

import com.example.demo.model.KetQua;
import com.example.demo.response.ScoreStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KetQuaRepository extends JpaRepository<KetQua, Integer>, KetQuaRepoCustom {

    @Query(nativeQuery = true,
            value = "select kq.* from ket_qua kq where kq.dkh_id = ?1 order by kq.d_id")
    List<KetQua> findAllByDkhId(Integer dkhId);
}
