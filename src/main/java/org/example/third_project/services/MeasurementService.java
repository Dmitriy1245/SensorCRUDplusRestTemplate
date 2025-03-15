package org.example.third_project.services;

import jakarta.validation.Valid;
import org.example.third_project.DTO.MeasurementDTO;
import org.example.third_project.DTO.SensorDTO;
import org.example.third_project.models.Measurement;
import org.example.third_project.models.Sensor;
import org.example.third_project.repositories.MeasurementRepository;
import org.example.third_project.util.MeasurementNotAddedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void save(MeasurementDTO measurementDTO) {
        if(sensorService.findById(measurementDTO.getSensor().getName()).isEmpty()){
            throw new MeasurementNotAddedException("No sensors with name '"+measurementDTO.getSensor().getName()+"' ");
        }
        else{
        Measurement currentMeasurement = convertMeasurementDTOToMeasurement(measurementDTO);
        currentMeasurement.setCurTime(LocalDateTime.now());
        measurementRepository.save(currentMeasurement);}

    }

    private Measurement convertMeasurementDTOToMeasurement(MeasurementDTO measurementDTO) {
        if (Objects.isNull(measurementDTO)) return null;
        Measurement measurement = new Measurement();
        measurement.setRaining(measurementDTO.isRaining());
        measurement.setSensor(convertSensorDTOToSensor(measurementDTO.getSensor()));
        measurement.setValue(measurementDTO.getValue());
        return measurement;
    }
    private MeasurementDTO convertMeasurementToMeasurementDTO(Measurement measurement) {
        if (Objects.isNull(measurement)) return null;
        MeasurementDTO measurementDTO = new MeasurementDTO();
        measurementDTO.setRaining(measurement.isRaining());
        measurementDTO.setSensor(convertSensorToSensorDTO(measurement.getSensor()));
        measurementDTO.setValue(measurement.getValue());
        return measurementDTO;
    }
    private Sensor convertSensorDTOToSensor(SensorDTO sensorDTO){
        Sensor sensor = new Sensor();
        sensor.setName(sensorDTO.getName());
        return sensor;
    }
    private SensorDTO convertSensorToSensorDTO(Sensor sensor){
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setName(sensor.getName());
        return sensorDTO;
    }

    public List<MeasurementDTO> getAll() {
        System.out.println("PRIVET");
        return measurementRepository.findAll().stream().map(this::convertMeasurementToMeasurementDTO).collect(Collectors.toList());
    }

    public int getRainyDaysCount() {
        return measurementRepository.countByRaining(true);
    }
}
