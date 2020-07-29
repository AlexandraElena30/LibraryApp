package com.example.online_library.controller;

import com.example.online_library.domain.model.PromotionDTO;
import com.example.online_library.exception.PromotionNotFoundException;
import com.example.online_library.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class PromotionController {

    private final PromotionService service;

    @GetMapping("/admin")
    public ModelAndView getPromotionById(ModelAndView model, @ModelAttribute("removeMessage") String message) {
        model.addObject("promotions", service.getAll());
        model.addObject("removeMessage", message);
        return model;
    }

    @PostMapping("/checkPromotion")
    public ModelAndView checkPromotion(@RequestParam("promotionCode") String promotionCode, RedirectAttributes ra) {
        ModelAndView model = new ModelAndView();
        try {
            PromotionDTO promo = service.findById(promotionCode);
            model.addObject("promoType", promo.getPromotionType());
            model.addObject("promoAmount", promo.getAmount());
            model.setViewName("redirect:/placedOrder");
            return model;
        } catch (PromotionNotFoundException e) {
            model.addObject("promoType", 0);
            model.addObject("promoAmount", 0);
            model.setViewName("redirect:/placedOrder");
            return model;
        }
    }

    @PostMapping("/addPromotion")
    public ModelAndView addPromotion(@RequestParam("code") String code, @RequestParam("type") Short type, @RequestParam("amount") int amount, ModelAndView model) {

        service.addPromotion(code, type, amount);
        model.setViewName("redirect:admin");
        return model;
    }

    @PostMapping("/removePromotion")
    public ModelAndView removePromotion(@RequestParam("code") String code, ModelAndView model) {
        Long ret = service.removePromotion(code);

        if (ret == 0) {
            model.addObject("removeMessage", "The promotion code was not found!");
        } else {
            model.addObject("removeMessage", "The promotion with code " + code + " was removed!");
        }
        model.setViewName("redirect:admin");
        return model;
    }
}
