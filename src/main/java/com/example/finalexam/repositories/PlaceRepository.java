package com.example.finalexam.repositories;

import com.example.finalexam.entities.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {

    Page<Place> findAll(Pageable pageable);
}
