package com.example.online_library.controller;

import com.example.online_library.domain.model.ProductsDTO;
import com.example.online_library.service.BookProductsService;
import com.example.online_library.service.CurrentUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class BookProductsController {

    private final BookProductsService service;

    @PostMapping("/products/{id}/book")
    public ModelAndView showBookProduct(@PathVariable("id") long bookProductId, ModelAndView model, @ModelAttribute ProductsDTO myEntity) {

        model.setViewName("product");
        model.addObject("product", myEntity);
        model.addObject("bookProduct", service.findById(bookProductId));
        if (CurrentUserService.logged == 1) {
            model.addObject("logged", "Logged");
        }
        return model;
    }
}
