package com.example.online_library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "bookproducts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookProductsEntity {

    @Id
    @Column(name = "bookid", nullable = false)
    private Long bookID;

    private String title;

    private String author;

    private String publisher;

    private int pages;

    private Short cover;

    @Column(name = "publishedyear", nullable = false)
    private LocalDate publishedYear;

    private String languages;

}
