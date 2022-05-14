package com.example.finalexam.forms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PlaceForm {

    @NotBlank(message = "Обязательное поле")
    private String name;

    @NotBlank(message = "Обязательное поле")
    private String description;

    private MultipartFile image;

    @AssertTrue(message = "Обязательное поле")
    public boolean isFileProvided() {
        return (image != null) && ( ! image.isEmpty());
    }
}
