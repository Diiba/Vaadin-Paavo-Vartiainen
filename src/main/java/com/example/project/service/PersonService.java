package com.example.project.service;

import com.example.project.entity.Person;
import com.example.project.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public List<Person> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Person save(Person person) {
        return repository.save(person);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
