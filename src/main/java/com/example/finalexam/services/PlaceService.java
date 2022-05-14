package com.example.finalexam.services;


import com.example.finalexam.dtos.PlaceDTO;
import com.example.finalexam.entities.Image;
import com.example.finalexam.entities.Place;
import com.example.finalexam.repositories.ImageRepository;
import com.example.finalexam.repositories.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    private final ImageRepository imageRepository;

    public List<PlaceDTO> findAllPlaces() {
        return placeRepository.findAll()
                .stream()
                .map(PlaceDTO::from)
                .collect(Collectors.toList());
    }


    public List<Image> getPlaceImages(Integer placeId) {
        return imageRepository.findAllByPlaceId(placeId);
    }

    public Integer addNewPlace(String name, String description, MultipartFile image) throws IOException {
        String path = "./src/main/resources/static/images";
        File imageFile = new File(path + "/" + image.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(imageFile);
        fos.write(image.getBytes());
        fos.close();

        Place place = Place.builder()
                .name(name)
                .description(description)
                .image("/images/" + image.getOriginalFilename())
                .build();
        placeRepository.save(place);

        return place.getId();
    }
}
