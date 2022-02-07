package de.logilutions.weatherservice.controller;

import de.logilutions.weatherservice.model.Measurement;
import de.logilutions.weatherservice.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("limit/{limit}")
    public List<Measurement> getAllLimitTo(@PathVariable int limit) {
        return measurementService.getAllLimitTo(limit);
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

    @GetMapping("unit/{key}/latest")
    public Measurement getLatestByUnit(@PathVariable String key) {
        return measurementService.getLatestByUnit(key);
    }

    @GetMapping("unit/{key}/limit/{limit}")
    public List<Measurement> getAllByUnit(@PathVariable String key, @PathVariable int limit) {
        return measurementService.getAllByUnitLimitTo(key, limit);
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


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Measurement putMeasurement(@RequestBody Measurement measurement) {
        return measurementService.saveMeasurement(measurement);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("all")
    public List<Measurement> putAllMeasurements(@RequestBody List<Measurement> measurements) {
        return measurementService.addAll(measurements);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Measurement postMeasurement(@RequestBody Measurement measurement) {
        return measurementService.addMeasurement(measurement);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteMeasurement(@PathVariable Long id) {
        measurementService.deleteMeasurement(id);
    }
}
