package com.example.demo.mapper;

import com.example.demo.dto.LordDto;
import com.example.demo.dto.PlanetDto;
import com.example.demo.entities.Lord;
import com.example.demo.entities.Planet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LordMapper extends AbstractMapper<Lord, LordDto>{
    public LordMapper() {
        super(Lord.class, LordDto.class);
    }
}
