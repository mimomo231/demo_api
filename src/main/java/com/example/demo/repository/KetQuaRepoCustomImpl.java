package com.example.demo.repository;

import com.example.demo.response.ScoreStatistic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class KetQuaRepoCustomImpl implements KetQuaRepoCustom{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ScoreStatistic> statisticScore() {
        StringBuilder sb = new StringBuilder();
        sb.append("select sum(test_function(kq.diem, dd.ti_le)) as diem_tk, sv.username from ket_qua kq " +
                "inner join dau_diem dd on dd.id = kq.d_id " +
                "inner join dang_ki_hoc dkh on dkh.id = kq.dkh_id " +
                "inner join thanh_vien sv on sv.id = dkh.sv_id " +
                "group by (kq.dkh_id) " +
                "having diem_tk > 4 " +
                "order by diem_tk");
        Query query = entityManager.createNativeQuery(sb.toString());
        return mapTo(query.getResultList());
    }
    private List<ScoreStatistic> mapTo(List<Object[]> rlist) {
        return rlist.stream()
                .map(r -> ScoreStatistic.builder()
                        .diemTk(((BigDecimal) r[0]).floatValue())
                        .username((String) r[1])
                        .type((((BigDecimal) r[0]).floatValue() < 6 ? "Trung binh" :
                                ((BigDecimal) r[0]).floatValue() < 9 ? "kha" : "gioi"))
                        .build())
                .collect(Collectors.toList());
    }
}
