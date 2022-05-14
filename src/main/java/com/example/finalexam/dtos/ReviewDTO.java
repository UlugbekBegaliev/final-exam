package com.example.finalexam.dtos;

import com.example.finalexam.entities.Review;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDTO {

    private int id;
    private int rating;
    private String content;
    private LocalDate date;
    private LocalTime time;

    public static ReviewDTO from(Review review){
        return builder()
                .id(review.getId())
                .rating(review.getRating())
                .content(review.getContent())
                .date(LocalDate.from(review.getReviewDate()))
                .time(LocalTime.from(review.getReviewTime()))
                .build();
    }

}

