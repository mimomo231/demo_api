package com.example.demo.service.impl;

import com.example.demo.model.BuoiHoc;
import com.example.demo.model.DangKiHoc;
import com.example.demo.model.LopHocPhan;
import com.example.demo.model.ThanhVien;
import com.example.demo.repository.BuoiHocRepository;
import com.example.demo.repository.DangKiHocRepository;
import com.example.demo.repository.LopHocPhanRepository;
import com.example.demo.request.DangKiMonHocRequest;
import com.example.demo.response.BuoiHocDTO;
import com.example.demo.service.DangKyMonHocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DangKyMonHocServiceImpl implements DangKyMonHocService {
    private final DangKiHocRepository repository;
    private final LopHocPhanRepository repository1;
    private final BuoiHocRepository repository2;
    @Override
    public ResponseEntity<?> checkDkh(DangKiMonHocRequest request) {
        ResponseEntity<?> result = null;
        // danh sach da dang ki
        List<Map<String, Object>> list = repository.test(request.getMaSinhVien(), request.getTen());
        boolean check = false;
        //lop hoc phan muon dang ki
        LopHocPhan lopHocPhan = repository1.findLopHocPhanById(request.getLhpId());
        // lich muon dang ki
        List<BuoiHocDTO> lichdk = repository2.findBuoiHocByLhpId(request.getLhpId()).stream()
                .map(this::mapTo)
                .collect(Collectors.toList());

//        for (BuoiHocDTO element : lichdk) {
//            System.out.println(element.getThId());
//        }
        // so sanh lich hoc muon voi lich lop hoc da dang ki
        for (Map<String, Object> lhp : list) {
            List<BuoiHocDTO> daDangKi = repository2.findBuoiHocByLhpId((Integer) lhp.get("lhp_id")).stream()
                    .map(this::mapTo)
                    .collect(Collectors.toList());
            // so sanh
            for (BuoiHocDTO bh: daDangKi) {
                for (BuoiHocDTO ld: lichdk) {
                    //kiem tra xem buoi hoc co bi trung voi nhau khong
                    if (bh.getThId() == ld.getThId() && bh.getLhpId() == ld.getLhpId() && bh.getNhId() == ld.getNhId()
                            && bh.getKhId() == ld.getKhId() && bh.getPhId() == ld.getPhId()){
                        result = ResponseEntity.ok("Thoi khoa bieu bi trung! Vui long chon lai!");
                        check = true;
                        break;
                    }
                }
                if (check) {
                    break;
                }
            }
        }

        if (lopHocPhan.getSiSoReal() == lopHocPhan.getSiSoMax()) {
            result = ResponseEntity.ok("het slot");
        }
        else if (!check) {
            DangKiHoc d = new DangKiHoc();

            ThanhVien v = new ThanhVien();
            v.setId((Integer) list.get(0).get("sv_id"));
            d.setSv(v);
            d.setDiemTb((float) 0);
            LopHocPhan l = new LopHocPhan();
            l.setId(request.getLhpId());
            d.setLhp(l);
            try {
                repository.save(d);
                // update lop hoc phan
                lopHocPhan.setSiSoReal(lopHocPhan.getSiSoReal() + 1);
                repository1.save(lopHocPhan);
            } catch (Exception e) {

            }
            result = ResponseEntity.ok("Dang ki thanh cong!");
        }
        else if (result == null) {
            result = ResponseEntity.ok(list);
        }

        return result;
    }
    public BuoiHocDTO mapTo(BuoiHoc buoiHoc){
        return BuoiHocDTO.builder()
                .lhpId(buoiHoc.getLhp().getId())
                .ten(buoiHoc.getTen())
                .khId(buoiHoc.getKh().getId())
                .nhId(buoiHoc.getNh().getId())
                .phId(buoiHoc.getPh().getId())
                .gvId(buoiHoc.getGv().getId())
                .thId(buoiHoc.getTh().getId())
                .build();
    }
}
