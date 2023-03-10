package com.example.demo.services;

import com.example.demo.dto.PlanetDto;
import com.example.demo.exception.PlanetNotFoundException;

public interface PlanetService {

    PlanetDto add(PlanetDto planetDto);

    void deleteById(Long id) throws PlanetNotFoundException;
}
