package com.example.online_library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookProductsDTO {

    private Long bookID;

    private String title;

    private String author;

    private String publisher;

    private int pages;

    private Short cover;

    private LocalDate publishedYear;

    private String languages;

}
