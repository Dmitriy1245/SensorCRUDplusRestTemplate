package org.example.third_project.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sensors")
public class Sensor implements Serializable {
    @Id
    @Column(name="name")
    @NotEmpty(message = "name shouldn't be empty!")
    @Size(min = 3,max = 30, message = "name should be between 3 and 30 characters")

    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

    public Sensor(String name, List<Measurement> measurements) {
        this.name = name;
        this.measurements = measurements;
    }
    public Sensor(){}
    public Sensor(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public Sensor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
