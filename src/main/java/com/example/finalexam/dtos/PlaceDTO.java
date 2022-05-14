package com.example.finalexam.dtos;

import com.example.finalexam.entities.Place;
import lombok.*;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceDTO {

    private int id;
    private String name;
    private String image;
    private String description;
    private double rating;

    public static PlaceDTO from(Place place){
        return builder()
                .id(place.getId())
                .name(place.getName())
                .image(place.getImage())
                .description(place.getDescription())
                .rating(Math.round(place.getRating()*10)/10.0)
                .build();
    }
}
