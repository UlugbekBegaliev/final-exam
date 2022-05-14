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

    private Integer id;
    private String login;
    private String content;
    private LocalDate date;
    private LocalTime time;
}

