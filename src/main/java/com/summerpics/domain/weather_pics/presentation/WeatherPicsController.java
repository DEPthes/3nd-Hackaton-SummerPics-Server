package com.summerpics.domain.weather_pics.presentation;

import com.summerpics.domain.weather_pics.application.WeatherPicsService;
import com.summerpics.domain.weather_pics.dto.request.ThumsRequestDTO;
import com.summerpics.domain.weather_pics.dto.response.ThumsResponseDTO;
import com.summerpics.domain.weather_pics.dto.WeatherPicsRankDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pic")
public class WeatherPicsController {
    @Autowired
    private final WeatherPicsService weatherPicsService;

    // 클릭 시 좋아요수 증가
    @PostMapping("/thums")
    public ResponseEntity<ThumsResponseDTO> updateThums(@RequestBody ThumsRequestDTO thumsRequestDTO) {
        ThumsResponseDTO response = weatherPicsService.updateThums(thumsRequestDTO);
//        if(response == null) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(response);
    }

    // 좋아요 수에 따른 랭킹
    @GetMapping("/rank")
    public List<WeatherPicsRankDTO> getWeatherPicsRank() {
        return weatherPicsService.getWeatherPicsRank();
    }
}

