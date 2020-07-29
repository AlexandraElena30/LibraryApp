package com.example.online_library.service;

import com.example.online_library.domain.entity.BookProductsEntity;
import com.example.online_library.domain.model.BookProductsDTO;
import com.example.online_library.exception.BookProductsNotFoundException;
import com.example.online_library.mapper.BookProductsEntityToBookProductsMapper;
import com.example.online_library.mapper.BookProductsToBookProductsEntityMapper;
import com.example.online_library.repository.BookProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class BookProductsService {

    private final BookProductsRepository repository;

    private final BookProductsEntityToBookProductsMapper bookProductsEntityToBookProductsMapper;

    private final BookProductsToBookProductsEntityMapper bookProductsToBookProductsEntityMapper;

    public BookProductsDTO create(@Valid BookProductsDTO bookProducts) {
        BookProductsEntity bookProductsEntity = bookProductsToBookProductsEntityMapper.convert(bookProducts);
        BookProductsEntity savedBookProductsEntity = repository.save(bookProductsEntity);
        return bookProductsEntityToBookProductsMapper.convert(savedBookProductsEntity);
    }

    public BookProductsDTO findById(long productId) {
        return repository.findById(productId)
                .map(bookProductsEntityToBookProductsMapper::convert)
                .orElseThrow(() -> new BookProductsNotFoundException("The book with specified id not found"));
    }

    public List<BookProductsDTO> getAll() {
        return repository.getAll()
                .stream()
                .map(bookProductsEntityToBookProductsMapper::convert)
                .collect(Collectors.toList());
    }
}
