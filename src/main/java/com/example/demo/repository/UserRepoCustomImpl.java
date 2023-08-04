package com.example.demo.repository;

import com.example.demo.response.SinhVienDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepoCustomImpl implements UserRepoCustom{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<SinhVienDTO> findAllUserByClass(Integer lid) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT sv.username, sv.ma_sinh_vien, sv.tel, sv.email FROM thanh_vien sv " +
                "inner join dang_ki_hoc dkh on dkh.sv_id = sv.id " +
                "where dkh.lhp_id = :lid");
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("lid", lid);
        return mapTo(query.getResultList());
    }
    private List<SinhVienDTO> mapTo(List<Object[]> resultList) {

        return resultList.stream().map(
                b -> SinhVienDTO.builder()
                        .username((String) b[0])
                        .msv((String) b[1])
                        .tel((String) b[2])
                        .email((String) b[3])
                        .build()).collect(Collectors.toList());
    }
}
