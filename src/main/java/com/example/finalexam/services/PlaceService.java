package com.example.finalexam.services;


import com.example.finalexam.dtos.PlaceDTO;
import com.example.finalexam.entities.Image;
import com.example.finalexam.entities.Place;
import com.example.finalexam.repositories.ImageRepository;
import com.example.finalexam.repositories.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

    public List<Place> searchPlaces(String searchText, Pageable pageable) {
        List<Place> places = placeRepository.findAll();
        List<Place> thisPlaces = new ArrayList<>();
        for(Place p: places){
            if(p.getName().toLowerCase().contains(searchText.toLowerCase())){
                thisPlaces.add(p);
            }else if(p.getDescription().toLowerCase().contains(searchText.toLowerCase())){
                thisPlaces.add(p);
            }
        }
        return thisPlaces;
    }

    public Integer addImage(MultipartFile image, Integer placeId) throws IOException {
        String path ="./src/main/resources/static/images";
        File imageFile = new File(path + "/" + image.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(imageFile);
        fos.write(image.getBytes());
        fos.close();

        Image image1 = Image.builder()
                .placeImage("/images/"+ image.getOriginalFilename())
                .placeId(placeId)
                .build();

        imageRepository.save(image1);

        return placeId;
    }
}
