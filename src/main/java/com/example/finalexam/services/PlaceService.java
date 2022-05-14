package com.example.finalexam.services;


import com.example.finalexam.dtos.PlaceDTO;
import com.example.finalexam.entities.Place;
import com.example.finalexam.repositories.ImageRepository;
import com.example.finalexam.repositories.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    private final ImageRepository imageRepository;

    public List<PlaceDTO> findAllPlaces(){
        return placeRepository.findAll()
                .stream()
                .map(PlaceDTO::from)
                .collect(Collectors.toList());
    }


}
