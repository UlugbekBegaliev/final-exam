package com.example.finalexam.controllers;

import com.example.finalexam.dtos.ReviewDTO;
import com.example.finalexam.entities.Image;
import com.example.finalexam.entities.Place;
import com.example.finalexam.repositories.PlaceRepository;
import com.example.finalexam.services.PagePropertyService;
import com.example.finalexam.services.PlaceService;
import com.example.finalexam.services.ReviewService;
import com.example.finalexam.utils.PageStructure;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class MainController {

    private final PlaceService placeService;
    private final ReviewService reviewService;
    private final PlaceRepository placeRepository;
    private final PagePropertyService pagePropertyService;

    @GetMapping("/")
    public String getMainPage(Model model, Pageable pageable, HttpServletRequest request) {
        Page<Place> places = placeRepository.findAll(pageable);
        String hsr = request.getRequestURI();
        Model placeModel = model.addAttribute("places", placeService.findAllPlaces());
        PageStructure.constructPageable(places, pagePropertyService.getDefaultPageSize(), placeModel, hsr);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        if (!userEmail.equals("anonymousUser")) {
            model.addAttribute("authorized", true);
        }
        return "main_page";
    }

    @PostMapping("/createReview")
    public String addNewReview(@RequestParam("place_id") Integer placeId,
                               @RequestParam("rating") Integer rating,
                               @RequestParam("message_content") String content) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        return reviewService.createNewReview(placeId, content, userEmail, rating);
    }

    @GetMapping("/create_place")
    public String createNewPlacePage(Model model) {
        return "create_place";
    }

    @PostMapping("/create_place")
    public String createNewPlace(@RequestParam("place_name") String name, @RequestParam("place_description") String description,
                                 @RequestParam("place_image") MultipartFile image) throws IOException {
        int id = placeService.addNewPlace(name, description, image);
        return "redirect:/places/" + id;
    }

    @GetMapping("/places/{id}")
    public String getPlacesPage(@PathVariable int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        List<Image> placeImage = placeService.getPlaceImages(id);
        if (placeImage != null) {
            model.addAttribute("placeImage", placeImage);
        }
        List<ReviewDTO> reviewDTOS = reviewService.getPlaceReviews(id);
        model.addAttribute("place", placeRepository.findById(id).get());
        if (reviewDTOS != null) {
            model.addAttribute("reviews", reviewDTOS);
        } else {
            model.addAttribute("reviews", false);
        }
        if (!userEmail.equals("anonymousUser")) {
            model.addAttribute("authorized", true);
        }
        List<Integer> rating = reviewService.getValue();
        model.addAttribute("rating", rating);
        return "place_page";
    }

    @GetMapping("/search/{search}")
    public String search(@PathVariable("search") String search, Principal principal,
                         Model model, Pageable pageable) {
        List<Place> places = placeService.searchPlaces(search, pageable);
        model.addAttribute("places", places);
        return "search";
    }

    @PostMapping("/add_image")
    public String addImage(@RequestParam("place_id") Integer placeId,
                           @RequestParam("place_image") MultipartFile image) throws IOException {
        if (image.getOriginalFilename().equals("")) {
            return "redirect:/places/" + placeId;
        }
        Integer id = placeService.addImage(image, placeId);
        return "redirect:/places/" + id;
    }
}
