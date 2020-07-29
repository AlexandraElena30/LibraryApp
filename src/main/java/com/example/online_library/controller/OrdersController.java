package com.example.online_library.controller;

import com.example.online_library.domain.model.ProductsDTO;
import com.example.online_library.service.CurrentUserService;
import com.example.online_library.service.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.util.ArrayList;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class OrdersController {

    private final OrdersService service;

    @GetMapping("newOrder")
    public RedirectView addOrder(@ModelAttribute("prods") ArrayList<ProductsDTO> productList, @ModelAttribute("price") BigDecimal price, RedirectAttributes ra) {

        service.addNewOrder(productList, price);
        ra.addFlashAttribute("price", price);
        RedirectView rv = new RedirectView("userUpdate");
        return rv;
    }

    @GetMapping("/account/orders")
    public ModelAndView getAllForUser(ModelAndView model) {
        model.addObject("orders", service.getAllForUser(CurrentUserService.currentUser.getUserID()));
        model.setViewName("orders");
        return model;
    }
}
