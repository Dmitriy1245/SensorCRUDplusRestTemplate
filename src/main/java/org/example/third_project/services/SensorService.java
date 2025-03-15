package org.example.third_project.services;


import org.example.third_project.DTO.SensorDTO;
import org.example.third_project.models.Sensor;
import org.example.third_project.repositories.SensorRepository;
import org.example.third_project.util.SensorNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    @Transactional
    public void save(SensorDTO sensorDTO){
        if(sensorRepository.findById(sensorDTO.getName()).isPresent()){
            throw new SensorNotCreatedException("Sensor with name '"+sensorDTO.getName()+"' already exists");
        }
        else sensorRepository.save(convertSensorDTOToSensor(sensorDTO));
    }

    public Optional<Sensor> findById(String name){
        return sensorRepository.findById(name);//сделать orElseTrow exception
    }
    private Sensor convertSensorDTOToSensor(SensorDTO sensorDTO){
        Sensor sensor = new Sensor();
        sensor.setName(sensorDTO.getName());
       // sensor.setMeasurements(sensorDTO.getMeasurements());
        return sensor;
    }
}
