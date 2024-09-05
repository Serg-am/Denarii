package com.denarii.service;

import com.denarii.entity.City;
import com.denarii.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getCitiesByCountryId(Integer countryId) {
        if (countryId == null || countryId <= 0) {
            throw new IllegalArgumentException("Invalid countryId");
        }
        return cityRepository.findByCountryId(countryId);
    }
}
