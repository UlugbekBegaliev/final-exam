package com.example.finalexam.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128)
    private String name;

    @Column(length = 128)
    private String image;

    @Column
    private String description;
}
