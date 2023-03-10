package com.example.demo.services;

import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Lord;
import com.example.demo.entities.Planet;
import com.example.demo.exception.PlanetNotFoundException;
import com.example.demo.mapper.PlanetMapper;
import com.example.demo.repositories.PlanetRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository planetRepository;
    private final PlanetMapper planetMapper;

    @Override
    public PlanetDto add(PlanetDto planetDto) {
        return planetMapper.toDto(
                planetRepository.save(new Planet(planetDto,null))
        );
    }

    public PlanetDto add(PlanetDto planetDto, Lord lord) {
        return planetMapper.toDto(
                planetRepository.save(new Planet(planetDto, lord))
        );
    }

    @Override
    public void deleteById(Long id) throws PlanetNotFoundException {
        try {
            planetRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new PlanetNotFoundException(id);
        }
    }

    public Planet getPlanetById(Long planetId) {
        return planetRepository.findById(planetId)
                .orElseThrow(() -> new RuntimeException("Planet with " + planetId + "not found"));
    }

    public Planet save(Planet planet) {
        return planetRepository.save(planet);
    }
}
