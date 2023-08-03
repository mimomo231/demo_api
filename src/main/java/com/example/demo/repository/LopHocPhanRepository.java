package com.example.demo.repository;

import com.example.demo.model.LopHocPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LopHocPhanRepository extends JpaRepository<LopHocPhan, Integer> {
    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE lop_hoc_phan l SET l.si_so_real = l.si_so_real + 1 WHERE l.id = :param1")
    void updateLopHocPhanNew(@Param("param1") Integer Id1);
    LopHocPhan findLopHocPhanById(Integer id);
}
