package com.example.demo.service;

import com.example.demo.Utils.Utils;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Planet;
import com.example.demo.exception.PlanetNotFoundException;
import com.example.demo.repositories.PlanetRepository;
import com.example.demo.services.PlanetServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;

import static com.example.demo.Utils.Utils.PLANET_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@SpringBootTest
class PlanetServiceImplUnitTest {

    @Autowired
    private PlanetServiceImpl planetService;
    @Autowired
    private Utils utils;
    @MockBean
    private PlanetRepository planetRepository;

    @Test
    void add_Success() {
        when(planetRepository.save(any(Planet.class)))
                .thenReturn(utils.getPlanet());
        PlanetDto planetDto = planetService.add(utils.getPlanetDto());
        assertEquals(utils.getPlanetDto(), planetDto);
        when(planetRepository.save(any(Planet.class)))
                .thenReturn(utils.getPlanetWithLord());
        planetDto = planetService.add(utils.getPlanetDtoWithLordDto());
        assertEquals(utils.getPlanetDtoWithLordDto(), planetDto);
    }

    @Test
    void deleteById_Success() {
        doThrow(new EmptyResultDataAccessException(1))
                .when(planetRepository).deleteById(PLANET_ID);
        assertThrows(PlanetNotFoundException.class,
                () -> planetService.deleteById(PLANET_ID));
    }

}