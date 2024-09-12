package com.denarii.controller;

import com.denarii.entity.Ad;
import com.denarii.entity.UserWebApp;
import com.denarii.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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



    // Показать список объявлений

    @GetMapping("/ads")
    public String listAds(
            @RequestParam(defaultValue = "1") int page,  // Текущая страница
            @RequestParam(defaultValue = "6") int size,  // Размер страницы
            Model model) {

        if (page < 1) {
            page = 1;  // Если пользователь вводит номер страницы меньше 1, устанавливаем на 1
        }

        // Создаем объект PageRequest для получения данных с пагинацией
        Page<Ad> adPage = adService.findAdsByPage(PageRequest.of(page - 1, size));  // Индекс страницы начинается с 0

        int totalPages = adPage.getTotalPages();  // Общее количество страниц

        model.addAttribute("ads", adPage.getContent());  // Список объявлений для текущей страницы
        model.addAttribute("currentPage", page);  // Текущая страница
        model.addAttribute("totalPages", totalPages);  // Общее количество страниц
        model.addAttribute("pageSize", size);  // Количество объявлений на странице

        return "ads";  // Возвращаем имя Thymeleaf шаблона для отображения
    }

}

