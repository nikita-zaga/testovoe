package com.example.demo.Utils;

import com.example.demo.dto.LordDto;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Lord;
import com.example.demo.entities.Planet;
import com.example.demo.mapper.LordMapper;
import com.example.demo.mapper.PlanetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
public class Utils {
    @Autowired
    private PlanetMapper planetMapper;
    @Autowired
    private LordMapper lordMapper;

    public static final Long LORD_ID = 1L;
    public static final Long PLANET_ID = 2L;
    public static final String SOME_NAME_LORD = "lordName";
    public static final String SOME_NAME_PLANET = "planetName";
    public static final Integer LORD_AGE = 7;

    public Lord getLordWithPlanet() {
        Planet planet = new Planet() {{
            setId(PLANET_ID);
            setName(SOME_NAME_PLANET);
        }};
        Lord lord = new Lord() {{
            setId(LORD_ID);
            setName(SOME_NAME_LORD);
            setAge(LORD_AGE);
            setPlanets(Collections.singletonList(planet));
        }};
        planet.setLord(lord);
        return lord;
    }

    public LordDto getLordDtoWithPlanetDto() {
        return lordMapper.toDto(getLordWithPlanet());
    }

    public LordDto getLordDto() {
        return lordMapper.toDto(getLord(SOME_NAME_LORD, LORD_AGE));
    }

    public Lord getLord(String name, Integer age) {
        return new Lord() {{
            setId(LORD_ID);
            setName(name);
            setAge(age);
            setPlanets(Collections.emptyList());
        }};
    }

    public Lord getLord() {
        return new Lord() {{
            setId(LORD_ID);
            setName(SOME_NAME_LORD);
            setAge(LORD_AGE);
            setPlanets(Collections.emptyList());
        }};
    }

    public PlanetDto getPlanetDtoWithLordDto() {
        return planetMapper.toDto(getPlanetWithLord());
    }

    public Planet getPlanetWithLord() {
        Planet planet = new Planet() {{
            setId(PLANET_ID);
            setName(SOME_NAME_PLANET);
        }};
        Lord lord = new Lord() {{
            setId(LORD_ID);
            setName(SOME_NAME_LORD);
            setAge(LORD_AGE);
            setPlanets(Collections.singletonList(planet));
        }};
        planet.setLord(lord);
        return planet;
    }

    public Planet getPlanet() {
        return new Planet() {{
            setId(PLANET_ID);
            setName(SOME_NAME_PLANET);
        }};
    }
    public PlanetDto getPlanetDto() {
        return getPlanetDto(SOME_NAME_PLANET);
    }

    public PlanetDto getPlanetDto(String name) {
        Planet planet = new Planet() {{
            setId(PLANET_ID);
            setName(name);
        }};
        return planetMapper.toDto(planet);
    }
}
