package org.example.third_project.controllers;

import jakarta.validation.Valid;
import org.example.third_project.DTO.SensorDTO;
import org.example.third_project.services.SensorService;
import org.example.third_project.util.SensorErrorResponse;
import org.example.third_project.util.SensorNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            for (FieldError error:bindingResult.getFieldErrors()){
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
            }
            throw new SensorNotCreatedException(errorMsg.toString());
        }
        sensorService.save(sensorDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException exception){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                exception.getMessage(),
                new Date()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
