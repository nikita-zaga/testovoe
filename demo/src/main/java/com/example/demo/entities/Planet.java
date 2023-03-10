package com.example.demo.entities;

import com.example.demo.dto.PlanetDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Planet extends AbstractEntity{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lord_id")
    @JsonIgnoreProperties({"planets"})
    private Lord lord;

    public Planet(PlanetDto planetDto, Lord lord) {
        super(planetDto.getName());
        this.lord = lord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Planet planet = (Planet) o;
        if (lord != null && planet.lord != null) {
            return Objects.equals(lord.getId(), planet.lord.getId());
        }
        return lord == null && planet.lord == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lord.getId());
    }
}
