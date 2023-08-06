package com.example.demo.repository;

import com.example.demo.model.ThanhVien;
import com.example.demo.request.ResetPasswordRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ThanhVien, Integer>, UserRepoCustom  {
//    default List<SinhVienDTO> findAllSinhVienByClass(Integer lhpId){
//        return this.findAll(new QueryCallBack<List<SinhVienDTO>>() {
//            @Override
//            public List<SinhVienDTO> doWithEntityManager(EntityManager entityManager) {
//                StringBuilder sb = new StringBuilder();
//                sb.append("SELECT sv.username, sv.ma_sinh_vine, sv.tel, sv.email FROM thanh_vien sv " +
//                        "inner join dang_ki_hoc dkh on dkh.sv_id = sv.id " +
//                        "where dkh.lhp_id = :lid");
//                Query query = entityManager.createNativeQuery(sb.toString());
//
//                return mapTo(query.getResultList());
//            }
//            private List<SinhVienDTO> mapTo(List<Object[]> resultList) {
//
//                return resultList.stream().map(
//                        b -> SinhVienDTO.builder()
//                                .username((String) b[0])
//                                .msv((String) b[1])
//                                .tel((String) b[2])
//                                .email((String) b[3])
//                                .build()).collect(Collectors.toList());
//            }
//        });
//    }
    Optional<ThanhVien> findByUsername(String username);
}
