package com.denarii.service;

import com.denarii.entity.Ad;
import com.denarii.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdService {

    private final AdRepository adRepository;

    @Autowired
    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public Ad saveAd(Ad ad) {
        return adRepository.save(ad);
    }

    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    public Page<Ad> findAdsByPage(PageRequest pageRequest) {
        return adRepository.findAll(pageRequest);
    }


    public Page<Ad> findAll(PageRequest pageRequest) {
        return adRepository.findAll(pageRequest);
    }

    public Optional<Ad> getAdById(Long id) {
        return adRepository.findById(id);
    }
}
