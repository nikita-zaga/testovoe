package com.example.demo.mapper;

import com.example.demo.dto.LordDto;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Lord;
import com.example.demo.entities.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetMapper extends AbstractMapper<Planet, PlanetDto> {
    public PlanetMapper() {
        super(Planet.class, PlanetDto.class);
    }
}
