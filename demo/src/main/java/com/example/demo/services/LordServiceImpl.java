package com.example.demo.services;

import com.example.demo.dto.LordDto;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Lord;
import com.example.demo.entities.Planet;
import com.example.demo.exception.LordNotFoundException;
import com.example.demo.mapper.LordMapper;
import com.example.demo.mapper.PlanetMapper;
import com.example.demo.repositories.LordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LordServiceImpl implements LordService {

    private final LordRepository lordRepository;
    private final PlanetServiceImpl planetService;
    private final LordMapper lordMapper;
    private final PlanetMapper planetMapper;

    @Override
    public LordDto add(LordDto lordDto) {
        return lordMapper.toDto(lordRepository.save(new Lord(lordDto)));
    }

    @Override
    public PlanetDto assignToManagePlanet(Long lordId, Long planetId) throws LordNotFoundException {
        Lord lord = getLordById(lordId);
        Planet planet = planetService.getPlanetById(planetId);
        planet.setLord(lord);
        return planetMapper.toDto(planetService.save(planet));
    }

    public Lord getLordById(Long id) throws LordNotFoundException {
        return lordRepository.findById(id)
                .orElseThrow(() -> new LordNotFoundException(id));
    }

    @Override
    public List<LordDto> getLordsWithOutPlanets() {
        return lordRepository.getLordsWithOutPlanets().stream()
                .map(lordMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<LordDto> getTopTenYoungestLords() {
        return lordRepository.findFirst10ByOrderByAge().stream()
                .map(lordMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlanetDto addPlanet(Long id, PlanetDto planetDto) throws LordNotFoundException {
        Lord lord = getLordById(id);
        return planetService.add(planetDto, lord);
    }
}
