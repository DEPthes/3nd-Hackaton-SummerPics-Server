
package com.summerpics.domain.weather_pics.application;

import com.summerpics.domain.weather_pics.domain.WeatherPics;
import com.summerpics.domain.weather_pics.domain.repository.WeatherPicsRepository;
import com.summerpics.domain.weather_pics.dto.request.ThumsRequestDTO;
import com.summerpics.domain.weather_pics.dto.response.ThumsResponseDTO;
import com.summerpics.domain.weather_pics.dto.WeatherPicsRankDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherPicsService {
    @Autowired
    private WeatherPicsRepository weatherPicsRepository;

    // 좋아요 수 업데이트
    public ThumsResponseDTO updateThums(ThumsRequestDTO thumsRequestDTO) {
        Optional<WeatherPics> optionalWeatherPics = weatherPicsRepository.findById(thumsRequestDTO.getPicture_id());
        if (optionalWeatherPics.isPresent()) {
            WeatherPics weatherPics = optionalWeatherPics.get();
            weatherPics.setThums(weatherPics.getThums()+1);
            weatherPicsRepository.save(weatherPics);
            return new ThumsResponseDTO(weatherPics.getPictureId(), weatherPics.getThums());
        } else {
            throw new IllegalArgumentException("Invalid picture_id");
        }
    }

    // 상위 짤 리스트
    public List<WeatherPicsRankDTO> getWeatherPicsRank() {
        List<WeatherPicsRankDTO> rankList = weatherPicsRepository.findTopRankings()
                .stream()
                .filter(wp -> wp.getThums() > 0)    // 좋아요 수가 0인 항목 제외
                .limit(10)  // 상위 10개만 출력
                .map(weatherPics -> new WeatherPicsRankDTO(
                        weatherPics.getPictureId(),
                        weatherPics.getPictureUrl(),
                        weatherPics.getThums(),
                        weatherPics.getTitle()
                ))
                .collect(Collectors.toList());

        return rankList;
    }
}
