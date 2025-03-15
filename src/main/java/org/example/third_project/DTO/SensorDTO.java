package org.example.third_project.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.example.third_project.models.Measurement;

import java.util.List;

public class SensorDTO {
    @NotEmpty(message = "name shouldn't be empty!")
    @Size(min = 3,max = 30, message = "name should be between 3 and 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
