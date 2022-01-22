package de.logilutions.weatherservice.controller;

import de.logilutions.weatherservice.model.Measurement;
import de.logilutions.weatherservice.model.Test;
import de.logilutions.weatherservice.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@RestController
@RequestMapping("api/v1/measurement/")
public class MeasurementController {
    private MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    public List<Measurement> getAll() {
        return measurementService.getAll();
    }

    @GetMapping("timestamp")
    public List<Measurement> getAllBetween(
            @RequestParam(name = "start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime start,

            @RequestParam(name = "end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime end) {
        return measurementService.getAllBetween(start, end);
    }

    @GetMapping("unit/{key}")
    public List<Measurement> getAllByUnit(@PathVariable String key) {
        return measurementService.getAllByUnit(key);
    }

    @GetMapping("timestamp/unit/{key}")
    public List<Measurement> getAllBetweenByUnit(
            @RequestParam(name = "start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime start,

            @RequestParam(name = "end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime end,

            @PathVariable
            String key) {
        return measurementService.getAllBetweenByUnit(start, end, key);
    }

    @GetMapping("{id}")
    public Measurement getById(@PathVariable Long id) {
        return measurementService.getById(id);
    }


    @PutMapping
    public Measurement putMeasurement(@RequestBody Measurement measurement) {
        return measurementService.saveMeasurement(measurement);
    }

    @PutMapping("all")
    public List<Measurement> putAllMeasurements(@RequestBody List<Measurement> measurements) {
        return measurementService.addAll(measurements);
    }

    @PostMapping
    public Measurement postMeasurement(@RequestBody Measurement measurement) {
        return measurementService.addMeasurement(measurement);
    }

    @DeleteMapping("{id}")
    public void deleteMeasurement(@PathVariable Long id) {
        measurementService.deleteMeasurement(id);
    }
}
