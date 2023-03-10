package com.example.demo.service;

import com.example.demo.Utils.Utils;
import com.example.demo.dto.LordDto;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Lord;
import com.example.demo.entities.Planet;
import com.example.demo.exception.LordNotFoundException;
import com.example.demo.repositories.LordRepository;
import com.example.demo.services.LordServiceImpl;
import com.example.demo.services.PlanetServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.demo.Utils.Utils.LORD_ID;
import static com.example.demo.Utils.Utils.PLANET_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class LordServiceImplUnitTest {

    @Autowired
    private LordServiceImpl lordService;
    @Autowired
    private Utils utils;

    @MockBean
    private LordRepository lordRepository;
    @MockBean
    private PlanetServiceImpl planetService;

    @Test
    void add_Success() {
        when(lordRepository.save(any(Lord.class)))
                .thenReturn(utils.getLord());
        LordDto lordDto = lordService.add(utils.getLordDto());
        assertEquals(utils.getLordDto(), lordDto);
    }

    @Test
    void addPlanet_Success() throws LordNotFoundException {
        when(lordRepository.findById(LORD_ID))
                .thenReturn(Optional.of(utils.getLord()));
        when(planetService.add(any(PlanetDto.class), eq(
                utils.getLord())))
                .thenReturn(utils.getPlanetDtoWithLordDto());
        PlanetDto planetDto = lordService.addPlanet(LORD_ID, utils.getPlanetDto());
        assertEquals(utils.getPlanetDtoWithLordDto(), planetDto);
    }

    @Test
    void assignToManagePlanet_Success() throws LordNotFoundException {
        when(lordRepository.findById(LORD_ID))
                .thenReturn(Optional.of(utils.getLord()));
        when(planetService.getPlanetById(PLANET_ID))
                .thenReturn(utils.getPlanet());
        when(planetService.save(any(Planet.class)))
                .thenReturn(utils.getPlanetWithLord());
        PlanetDto planetDto = lordService.assignToManagePlanet(LORD_ID, PLANET_ID);
        assertEquals(utils.getPlanetDtoWithLordDto(), planetDto);
    }

    @Test
    void getTopTenYoungestLords_Success() {
        when(lordRepository.findFirst10ByOrderByAge())
                .thenReturn(Collections.singletonList(utils.getLord()));
        List<LordDto> topTen = lordService.getTopTenYoungestLords();
        assertEquals(Collections.singletonList(utils.getLordDto()), topTen);
        when(lordRepository.findFirst10ByOrderByAge())
                .thenReturn(Collections.singletonList(utils.getLordWithPlanet()));
        topTen = lordService.getTopTenYoungestLords();
        assertEquals(Collections.singletonList(utils.getLordDtoWithPlanetDto()), topTen);
    }

    @Test
    void getLoafers_Success() {
        when(lordRepository.getLordsWithOutPlanets())
                .thenReturn(Collections.singletonList(utils.getLord()));
        List<LordDto> loafers = lordService.getLordsWithOutPlanets();
        assertEquals(Collections.singletonList(utils.getLordDto()), loafers);
    }
}
