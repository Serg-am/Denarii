package com.denarii.controller;

import com.denarii.entity.City;
import com.denarii.entity.Country;
import com.denarii.service.CityService;
import com.denarii.service.CountryService;
import com.denarii.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public String index(Model model) {
        // Загрузим все страны и валюты при загрузке страницы
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        return "index";
    }

    @GetMapping("/cities")
    @ResponseBody
    public List<City> getCitiesByCountry(@RequestParam("countryId") int countryId) {
        return cityService.getCitiesByCountryId(countryId);
    }





    @RequestMapping(value="/logout", method= RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
