package com.example.demo.repository;

import com.example.demo.response.LopHocPhanDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class LopHocPhanRepoCustomImpl implements LopHocPhanRepoCustom{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<LopHocPhanDTO> findAllClassByTeacher(Integer tid, String kten) {
        StringBuilder sb = new StringBuilder();
        sb.append("select lhp.*,mh.ten as ten_mon from giang_vien gv " +
                "inner join buoi_hoc bh on bh.gv_id = gv.id " +
                "inner join lop_hoc_phan lhp on lhp.id = bh.lhp_id " +
                "inner join mon_hoc_ki_hoc mhkh on mhkh.id = lhp.mhkh_id " +
                "inner join mon_hoc mh on mh.id = mhkh.mh_id " +
                "inner join ki_hoc kh on kh.id = mhkh.k_id " +
                "where gv.id = :tid and kh.ten = :kten " +
                "group by(lhp.id)");
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("tid", tid);
        query.setParameter("kten", kten);
        return mapTo(query.getResultList());
    }

    private List<LopHocPhanDTO> mapTo(List<Object[]> resultList) {
        return resultList.stream()
                .map(l -> LopHocPhanDTO.builder()
                        .maLop((Integer) l[0])
                        .moTa((String) l[1])
                        .siSoMax((Integer) l[2])
                        .siSoReal((Integer) l[3])
                        .tenLop((String) l[4])
                        .tenMon((String) l[6])
                        .build())
                .collect(Collectors.toList());
    }
}
