package com.example.demo.repository;

import com.example.demo.model.KetQua;
import com.example.demo.request.EnterScoreRequest;
import com.example.demo.response.ScoreStatistic;

import java.util.List;

public interface KetQuaRepoCustom {
    List<ScoreStatistic> statisticScore();
}
