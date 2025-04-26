package com.example.project.entity;

import jakarta.persistence.*;

@Entity
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timestamp;
    private int systolic;
    private int diastolic;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Measurement() {}

    public Measurement(String timestamp, int systolic, int diastolic, double weight, Person person) {
        this.timestamp = timestamp;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.weight = weight;
        this.person = person;
    }

    public Long getId() { return id; }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public int getSystolic() { return systolic; }

    public void setSystolic(int systolic) { this.systolic = systolic; }

    public int getDiastolic() { return diastolic; }

    public void setDiastolic(int diastolic) { this.diastolic = diastolic; }

    public double getWeight() { return weight; }

    public void setWeight(double weight) { this.weight = weight; }

    public Person getPerson() { return person; }

    public void setPerson(Person person) { this.person = person; }
}
