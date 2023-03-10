package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Objects;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanetDto extends AbstractDto {

    @JsonIgnoreProperties({"planets"})
    private LordDto lord;

    public PlanetDto(String name) {
        super(null, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PlanetDto planet = (PlanetDto) o;
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
