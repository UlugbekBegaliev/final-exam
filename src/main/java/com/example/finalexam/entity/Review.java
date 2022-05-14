package com.example.finalexam.entity;

import lombok.*;

import javax.persistence.*;

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
}
