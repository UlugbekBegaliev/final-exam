package com.example.finalexam.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor (access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128)
    private Integer placeId;

    @Column(length = 128)
    private Integer userId;

    @Column(length = 128)
    private Integer rating;

    @Column(length = 128)
    private String content;

    @Column
    private LocalDate reviewDate;

    @Column
    private LocalTime reviewTime;
}
