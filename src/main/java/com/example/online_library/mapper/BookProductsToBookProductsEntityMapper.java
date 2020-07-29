package com.example.online_library.mapper;

import com.example.online_library.domain.entity.BookProductsEntity;
import com.example.online_library.domain.model.BookProductsDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
@AllArgsConstructor
public class BookProductsToBookProductsEntityMapper implements Converter<BookProductsDTO, BookProductsEntity> {
    @Override
    public BookProductsEntity convert(BookProductsDTO source) {
        return BookProductsEntity.builder()
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
