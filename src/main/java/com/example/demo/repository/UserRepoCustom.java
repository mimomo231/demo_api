package com.example.demo.repository;

import com.example.demo.response.SinhVienDTO;

import java.util.List;

public interface UserRepoCustom {
    List<SinhVienDTO> findAllUserByClass(Integer lid);
    List<SinhVienDTO> searchUser(String key,int page, int size, String sortBy);
}
