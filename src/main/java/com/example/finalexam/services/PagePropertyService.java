package com.example.finalexam.services;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PagePropertyService {

    private final SpringDataWebProperties springDataWebProperties;

    public int getDefaultPageSize() {
        return springDataWebProperties.getPageable().getDefaultPageSize();
    }
}
