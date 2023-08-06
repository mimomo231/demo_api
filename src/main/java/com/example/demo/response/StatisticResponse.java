package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter @Setter
@AllArgsConstructor
public class StatisticResponse {
    private Map<String, Integer> tk;
    @JsonProperty("list_sinh_vien")
    private List<ScoreStatistic> listSinhVien;
    public StatisticResponse(List<ScoreStatistic> listSinhVien) {
        this.listSinhVien = listSinhVien;
        doStatistic(this.listSinhVien);
    }

    private void doStatistic(List<ScoreStatistic> listSinhVien) {
        tk = new HashMap<>();
        List<String> types = listSinhVien.stream()
                .map(ScoreStatistic::getType)
                .collect(Collectors.toList());
        tk.put("Trung binh", Collections.frequency(types, "Trung binh"));
        tk.put("kha", Collections.frequency(types, "kha"));
        tk.put("gioi", Collections.frequency(types, "gioi"));
        tk.put("Tong so", types.size());
    }

}
