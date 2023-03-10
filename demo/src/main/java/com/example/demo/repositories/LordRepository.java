package com.example.demo.repositories;

import com.example.demo.entities.Lord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LordRepository extends JpaRepository<Lord, Long> {

    @Query("SELECT DISTINCT ld FROM Lord ld "
            + "LEFT JOIN Planet pl ON (ld.id = pl.lord.id) WHERE pl.lord IS NULL")
    List<Lord> getLordsWithOutPlanets();


    List<Lord> findFirst10ByOrderByAge();

}
