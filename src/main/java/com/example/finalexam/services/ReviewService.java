package com.example.finalexam.services;

import com.example.finalexam.dtos.ReviewDTO;
import com.example.finalexam.entities.Place;
import com.example.finalexam.entities.Review;
import com.example.finalexam.repositories.PlaceRepository;
import com.example.finalexam.repositories.ReviewRepository;
import com.example.finalexam.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public List<ReviewDTO> getPlaceReviews(int placeId) {
        List<Review> messages = reviewRepository.findAllByPlaceId(placeId);
        List<ReviewDTO> messagesDTOList = new ArrayList<>();
        if (messages != null) {
            for (Review r : messages) {
                String userLogin = userRepository.findUserById(r.getUserId()).getLogin();
                messagesDTOList.add(ReviewDTO.builder()
                        .id(r.getId())
                        .login(userLogin)
                        .content(r.getContent())
                        .date(r.getReviewDate())
                        .time(r.getReviewTime())
                        .build());
            }
            return messagesDTOList;
        }
        return null;
    }

    public List<Integer> getValue() {
        List<Integer> valueArray = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            valueArray.add(i);
        }
        return valueArray;
    }

    public String createNewReview(Integer placeId, String content, String userEmail, Integer rating) {
        Integer id = userRepository.findUserByEmail(userEmail).getId();
        reviewRepository.save(Review.builder()
                .placeId(placeId)
                .content(content)
                .userId(id)
                .rating(rating)
                .reviewDate(LocalDate.now())
                .reviewTime(LocalTime.now())
                .build());

        Place place = placeRepository.findById(placeId).get();
        place.setRating(placeRating(rating, placeId));
        placeRepository.save(place);
        return "success";
    }

    private double placeRating(Integer placeRating, Integer placeId) {
        List<Review> reviews = reviewRepository.findAllByPlaceId(placeId);
        double ratingSum = 0;
        int count = 1;
        if (reviews != null) {
            for (int i = 0; i < reviews.size(); i++) {
                ratingSum = ratingSum + reviews.get(i).getRating();
                count++;
            }
            ratingSum = ratingSum + placeRepository.findById(placeId).get().getRating();
            return ratingSum / count;
        } else {
            return placeRepository.findById(placeId).get().getRating();
        }
    }
}
