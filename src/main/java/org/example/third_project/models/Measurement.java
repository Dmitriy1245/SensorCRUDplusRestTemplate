package org.example.third_project.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @Min(value = -80, message = "value should be from -80 to 80 degrees")
    @Max(value = 80, message = "value should be from -80 to 80 degrees")
    private float value;

    @Column(name = "raining")
    //@NotEmpty(message = "field 'raining' should be not empty")
    private boolean raining;

    @Column(name = "cur_time")
    private LocalDateTime curTime;

    @ManyToOne
    @JoinColumn(name = "sensor_name")
    //@NotEmpty(message = "field 'sensor' should be not empty")
    private Sensor sensor;
    public Measurement(){}
    public Measurement(int id, float value, boolean raining, Sensor sensor) {
        this.id = id;
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public LocalDateTime getCurTime() {
        return curTime;
    }

    public void setCurTime(LocalDateTime currentTime) {
        this.curTime = currentTime;
    }

    public Measurement(float value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }
}
