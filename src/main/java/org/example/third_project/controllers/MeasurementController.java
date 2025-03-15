package org.example.third_project.controllers;

import jakarta.validation.Valid;
import org.example.third_project.DTO.MeasurementDTO;
import org.example.third_project.models.Measurement;
import org.example.third_project.services.MeasurementService;
import org.example.third_project.util.MeasurementErrorResponse;
import org.example.third_project.util.MeasurementNotAddedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            for (FieldError error:bindingResult.getFieldErrors()){
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
            }
            throw new MeasurementNotAddedException(errorMsg.toString());
        }
        measurementService.save(measurementDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<MeasurementDTO> getAll(){
        return measurementService.getAll();
    }
    @GetMapping("/rainyDaysCount")
    public int rainyDaysCount(){
        return measurementService.getRainyDaysCount();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotAddedException exception){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                exception.getMessage(),
                new Date()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
