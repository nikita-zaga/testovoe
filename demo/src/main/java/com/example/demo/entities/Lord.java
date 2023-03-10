package com.example.demo.entities;

import com.example.demo.dto.LordDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter(value = AccessLevel.PROTECTED)
@Entity
@Table(name = "Lord")
@NoArgsConstructor
public class Lord  extends AbstractEntity{
    private Integer age;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "lord")
    @JsonIgnoreProperties({"lord"})
    private List<Planet> planets = new ArrayList<>();

    public Lord(LordDto lordDto) {
        super(lordDto.getName());
        this.age = lordDto.getAge();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Lord lord = (Lord) o;
        return Objects.equals(age, lord.age)
                && Objects.equals(planets, lord.planets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), age, planets);
    }
}
