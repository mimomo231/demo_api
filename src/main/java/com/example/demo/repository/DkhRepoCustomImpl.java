package com.example.demo.repository;

import com.example.demo.model.*;
import com.example.demo.response.DangKyHocDTO;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;

public class DkhRepoCustomImpl implements DkhRepoCustom{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<DangKyHocDTO> checkDkh(String code, String semester) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DangKyHocDTO> query = cb.createQuery(DangKyHocDTO.class);
        Root<DangKiHoc> root = query.from(DangKiHoc.class);// lay tu bang
        Join<ThanhVien, DangKiHoc> join1 = root.join("sv");
//        System.out.println(join1.getAttribute());
//        Join<DangKiHoc, LopHocPhan> join2 = join1.join("lhp");
//        Join<LopHocPhan, MonHocKiHoc> join3 = join2.join("mhkh");
//        Join<MonHocKiHoc, KiHoc> join4 = join3.join("kiHoc");
        query.where(cb.equal(root.get("sv").get("maSinhVien"), code));
//        query.where(cb.equal(root.get("lhp").get("mhkh").get("kiHoc").get("ten"), semester));
        query.select(cb.construct(DangKyHocDTO.class, root.get("id"), root.get("diemTb"), root.get("lhp").get("id"), root.get("sv").get("id")));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Map<String, Object> > test(String code, String semester) {
        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT * FROM dang_ki_hoc");
        sb.append("SELECT dkh.id, dkh.diem_tb, dkh.lhp_id, dkh.sv_id " +
                "FROM thanh_vien sv " +
                "inner join dang_ki_hoc dkh on dkh.sv_id = sv.id " +
                "inner join lop_hoc_phan lhp on dkh.lhp_id = lhp.id " +
                "inner join mon_hoc_ki_hoc mhkh on mhkh.id = lhp.mhkh_id " +
                "inner join ki_hoc kh on kh.id = mhkh.k_id " +
                "where sv.ma_sinh_vien = :msv and kh.ten = :kten");
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("msv", code);
        query.setParameter("kten", semester);
        return buildQueryParameters(query.getResultList());
    }
    private List<Map<String, Object> > buildQueryParameters(List<Object[]> list) {
        List<Map<String, Object> > result = new ArrayList<>();
        list.forEach(
                d -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", d[0]);
                    map.put("diem_tb", d[1]);
                    map.put("lhp_id", d[2]);
                    map.put("sv_id", d[3]);
                    result.add(map);
                }
        );
        return result;
    }
}
