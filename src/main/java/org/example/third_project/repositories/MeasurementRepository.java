package org.example.third_project.repositories;


import org.example.third_project.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    int countByRaining(boolean isRaining);
}
