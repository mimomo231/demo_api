package com.example.demo.repository;

import com.example.demo.response.BuoiHocDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuoiHocRepoCustomImpl implements BuoiHocRepoCustom{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BuoiHocDTO> findTkbByStudentAndSemester(String code, String semester) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT bh.* FROM thanh_vien sv " +
                "inner join dang_ki_hoc dkh on dkh.sv_id = sv.id " +
                "inner join lop_hoc_phan lhp on lhp.id = dkh.lhp_id " +
                "inner join buoi_hoc bh on bh.lhp_id = lhp.id " +
                "inner join mon_hoc_ki_hoc mhkh on mhkh.id = lhp.mhkh_id " +
                "inner join ki_hoc kh on kh.id = mhkh.k_id " +
                "where sv.ma_sinh_vien = :msv and kh.ten = :kten");
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("msv", code);
        query.setParameter("kten", semester);
        return buildQueryParameters(query.getResultList());
    }

    private List<BuoiHocDTO> buildQueryParameters(List<Object[]> resultList) {
        System.out.println(resultList);
        List<BuoiHocDTO> result = resultList.stream().map(
                b -> BuoiHocDTO.builder()
                        .ten((String) b[2])
                        .lhpId((Integer) b[5])
                        .gvId((Integer) b[3])
                        .phId((Integer) b[7])
                        .khId((Integer) b[4])
                        .nhId((Integer) b[6])
                        .thId((Integer) b[8])
                        .build()).collect(Collectors.toList());
        return result;
    }
}
