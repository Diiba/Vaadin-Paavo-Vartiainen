package com.example.project.repository;

import com.example.project.entity.Measurement;
import com.example.project.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    List<Measurement> findByPerson(Person person);
}
