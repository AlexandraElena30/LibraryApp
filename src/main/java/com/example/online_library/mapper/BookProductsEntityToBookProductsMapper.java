package com.example.online_library.mapper;

import com.example.online_library.domain.entity.BookProductsEntity;
import com.example.online_library.domain.model.BookProductsDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
@AllArgsConstructor
public class BookProductsEntityToBookProductsMapper implements Converter<BookProductsEntity, BookProductsDTO> {
    @Override
    public BookProductsDTO convert(BookProductsEntity source) {
        return BookProductsDTO.builder()
                .bookID(source.getBookID())
                .title(source.getTitle())
                .author(source.getAuthor())
                .publisher(source.getPublisher())
                .pages(source.getPages())
                .cover(source.getCover())
                .publishedYear(source.getPublishedYear())
                .languages(source.getLanguages())
                .build();
    }
}


