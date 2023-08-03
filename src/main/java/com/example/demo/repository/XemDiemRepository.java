package com.example.demo.repository;


import com.example.demo.model.DangKiHoc;
import com.example.demo.model.KetQua;
import com.example.demo.response.XemDiemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface XemDiemRepository extends JpaRepository<XemDiemResponse,Integer>{
    @Query(nativeQuery = true,
            value = "SELECT dkh.*, kq.diem FROM ki_hoc k " +
                    "INNER JOIN mon_hoc_ki_hoc mhkh ON mhkh.k_id = k.id " +
                    "INNER JOIN mon_hoc mh ON mh.id = mhkh.mh_id " +
                    "INNER JOIN dau_diem dd ON dd.mh_id = mh.id " +
                    "INNER JOIN ket_qua kq ON kq.d_id = dd.id " +
                    "INNER JOIN dang_ki_hoc dkh ON dkh.id = kq.dkh_id " +
                    "INNER JOIN thanh_vien sv ON sv.id = dkh.sv_id " +
                    "WHERE sv.ma_sinh_vien = ?1 AND k.ten = ?2")
    List<XemDiemResponse> findAllBySemesterAndStudentCode(String code, String semester);
    @Query(nativeQuery = true,
            value = "SELECT dkh.* FROM thanh_vien sv " +
                    "inner join dang_ki_hoc dkh on dkh.sv_id = sv.id " +
                    "inner join lop_hoc_phan lhp on dkh.lhp_id = lhp.id " +
                    "inner join mon_hoc_ki_hoc mhkh on mhkh.id = lhp.mhkh_id " +
                    "inner join ki_hoc kh on kh.id = mhkh.k_id")
    List<XemDiemResponse> findDkh(String code, String semester);
}
