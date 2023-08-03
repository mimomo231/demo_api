package com.example.demo.repository;

import com.example.demo.model.BuoiHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BuoiHocRepository extends JpaRepository<BuoiHoc, Integer>, BuoiHocRepoCustom {
    @Query(nativeQuery = true,
            value = "SELECT b.id ,b.mo_ta, b.ten, b.gv_id, b.kh_id, b.lhp_id, b.nh_id, b.ph_id, b.th_id " +
                    "FROM buoi_hoc b WHERE b.lhp_id = ?1")
    List<BuoiHoc> findBuoiHocByLhpId(Integer id);
}
