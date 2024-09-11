package com.denarii.controller;

import com.denarii.entity.Ad;
import com.denarii.entity.Currency;
import com.denarii.entity.UserWebApp;
import com.denarii.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class AdController {

    @Autowired
    private AdService adService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private UserService userService;

    // Показать форму для добавления объявления
    @GetMapping("/ads/new")
    public String showCreateAdForm(Model model) {
        model.addAttribute("ad", new Ad());
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        return "adt";
    }



    // Обработка формы создания объявления
    @PostMapping("/ads/new")
    public String createAd(@ModelAttribute("ad") Ad ad, Principal principal) {
        // Получаем текущего пользователя из Principal
        String username = principal.getName();
        UserWebApp user = userService.findByRegisterName(username);  // Замените на ваш метод для получения пользователя

        // Устанавливаем пользователя в объявлении
        ad.setUser(user);

        System.out.println("!!!!!!!!!!!!!!!!!" + ad.getUser().getUsername());
        System.out.println("!!!!!!!!!!!!!!!!!" + ad.getCountry().getName());
        System.out.println("!!!!!!!!!!!!!!!!!" + ad.getCity().getName());
        System.out.println("!!!!!!!!!!!!!!!!!" + ad.getDescription());
        System.out.println("!!!!!!!!!!!!!!!!!" + ad.getExchangeRate());
        System.out.println("!!!!!!!!!!!!!!!!!" + ad.getMaxAmount());
        System.out.println("!!!!!!!!!!!!!!!!!" + ad.getMinAmount());
        System.out.println("!!!!!!!!!!!!!!!!!" + ad.isBankTransferAvailable());
        if (ad.getCurrencyBuy() != null) {
            System.out.println("!!!!!!!!!!!!!!!!!" + ad.getCurrencyBuy().getName());
        } else {
            System.out.println("!!!!!!!!!!!!!!!!!" + "CurrencyBuy is null");
        }
        if (ad.getCurrencySell() != null) {
            System.out.println("!!!!!!!!!!!!!!!!!" + ad.getCurrencySell().getName());
        } else {
            System.out.println("!!!!!!!!!!!!!!!!!" + "CurrencySell is null");
        }


        System.out.println("Попытка сохранить");
        adService.saveAd(ad);
        System.out.println("Сохранено");
        return "redirect:/ads/new";
    }
}

