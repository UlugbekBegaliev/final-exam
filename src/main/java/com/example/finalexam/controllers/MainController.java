package com.example.finalexam.controllers;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
@AllArgsConstructor
public class MainController {

    private final PlaceService placeService;
    private final ReviewService reviewService;
    private final PlaceRepository placeRepository;
    private final PagePropertyService pagePropertyService;

    @GetMapping("/")
    public String getMainPage(Model model, Pageable pageable, HttpServletRequest request){
        Page<Place> places = placeRepository.findAll(pageable);
        String hsr = request.getRequestURI();
        Model placeModel = model.addAttribute("places", placeService.findAllPlaces());
        PageStructure.constructPageable(places, pagePropertyService.getDefaultPageSize(), placeModel, hsr);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        if (!userEmail.equals("anonymousUser")){
            model.addAttribute("authorized", true);
        }
        return "main-page";
    }
}
