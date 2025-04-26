package com.example.project.service;

import com.example.project.entity.Measurement;
import com.example.project.entity.Person;
import com.example.project.repository.MeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository repository;

    public MeasurementService(MeasurementRepository repository) {
        this.repository = repository;
    }

    public List<Measurement> findByPerson(Person person) {
        return repository.findByPerson(person);
    }

    public Measurement save(Measurement measurement) {
        return repository.save(measurement);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
