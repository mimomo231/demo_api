package com.example.demo.repository;

import com.example.demo.response.SinhVienDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepoCustomImpl implements UserRepoCustom {
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

    @Override
    public List<SinhVienDTO> searchUser(String key, int page, int size, String sortBy) {
//        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        try {
            StringBuilder sb = new StringBuilder();
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("search_user");
            query.registerStoredProcedureParameter("col", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("kw", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("s", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("e", Integer.class, ParameterMode.IN);

            query.setParameter("col", sortBy);
            query.setParameter("kw", key);
            query.setParameter("s", page*size);
            query.setParameter("e", size);

            return mapTo(query.getResultList());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }
    private List<SinhVienDTO> mapTo(List<Object[]> resultList) {

        return resultList.stream().map(
                b -> SinhVienDTO.builder()
                        .username((String) b[4])
                        .msv((String) b[5])
                        .tel((String) b[3])
                        .email((String) b[1])
                        .build()).collect(Collectors.toList());
    }
}
