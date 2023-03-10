package com.example.demo.controllers;

import com.example.demo.dto.PlanetDto;
import com.example.demo.exception.PlanetNotFoundException;
import com.example.demo.services.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("planet")
public class PlanetController {

    PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<Object> add(@RequestBody PlanetDto planetDto) {
        PlanetDto planet = planetService.add(planetDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(planet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(
            @PathVariable Long id
    ) throws PlanetNotFoundException {
        planetService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("{\"status\": 204}");
    }
}
