package com.summerpics.domain.weather_pics.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="WeatherPics")
@NoArgsConstructor
@Data
public class WeatherPics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="picture_id", updatable = false)
    private Long pictureId;

    @Column(name="picture_url")
    private String pictureUrl;

    @Column(name="thums")
    private int thums;

    @Column(name="title")
    private String title;

    @Builder
    public WeatherPics(Long pictureId, String pictureUrl, int thums, String title) {
        this.pictureId = pictureId;
        this.pictureUrl = pictureUrl;
        this.thums = thums;
        this.title = title;
    }

    public void increaseThums() {
        this.thums++;
    }

    public void decreaseThums() {
        if(this.thums > 0) {
            this.thums--;
        }
    }
}
