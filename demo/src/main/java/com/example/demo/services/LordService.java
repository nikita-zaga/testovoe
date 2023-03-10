package com.example.demo.services;

import com.example.demo.dto.LordDto;
import com.example.demo.dto.PlanetDto;
import com.example.demo.exception.LordNotFoundException;

import java.util.List;

public interface LordService {

    LordDto add(LordDto lordDto);

    PlanetDto assignToManagePlanet(Long lordId, Long planetId) throws LordNotFoundException;

    List<LordDto> getLordsWithOutPlanets();

    List<LordDto> getTopTenYoungestLords();

    PlanetDto addPlanet(Long id, PlanetDto planetDto) throws LordNotFoundException;
}
