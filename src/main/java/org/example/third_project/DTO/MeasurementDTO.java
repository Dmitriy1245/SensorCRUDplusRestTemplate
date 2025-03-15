package org.example.third_project.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.example.third_project.models.Sensor;

public class MeasurementDTO {

    @Min(value = -80, message = "value should be from -79 to 79 degrees")
    @Max(value = 80, message = "value should be from -79 to 79 degrees")
    private float value;
    //@NotEmpty(message = "field 'raining' should be not empty")
    private boolean raining;
    //@NotEmpty(message = "field 'sensor' should be not empty")
    private SensorDTO sensor;

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

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
