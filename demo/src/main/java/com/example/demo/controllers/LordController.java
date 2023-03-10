package com.example.demo.controllers;


import com.example.demo.dto.LordDto;
import com.example.demo.dto.PlanetDto;
import com.example.demo.exception.LordNotFoundException;
import com.example.demo.services.LordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "lord", produces = MediaType.APPLICATION_JSON_VALUE)
public class LordController {
    LordService lordService;

    public LordController(LordService lordService) {
        this.lordService = lordService;
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<Object> add(@RequestBody LordDto lordDto) {
        LordDto lord = lordService.add(lordDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(lord);
    }

    @PostMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> addPlanet(
            @PathVariable("id") Long id,
            @RequestBody PlanetDto planetDto
    ) throws LordNotFoundException {
        PlanetDto planet = lordService.addPlanet(id, planetDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(planet);
    }


    @PutMapping("assign_planet/{id}")
    @ResponseBody
    public ResponseEntity<Object> assignPlanetToLord(
            @PathVariable("id") Long lordId,
            @RequestParam("planet_id") Long planetId
    ) throws LordNotFoundException {

        PlanetDto update = lordService.assignToManagePlanet(lordId, planetId);
        return ResponseEntity.ok(update);
    }

    @GetMapping("/noplanet")
    public ResponseEntity<Object> getLordsWithOutPlanets() {
        List<LordDto> lords = lordService.getLordsWithOutPlanets();

        return ResponseEntity.ok(lords);
    }

    @GetMapping("/topten")
    public ResponseEntity<Object> getTopTenYoungestLords() {
        List<LordDto> lords = lordService.getTopTenYoungestLords();

        return ResponseEntity.ok(lords);
    }


}
