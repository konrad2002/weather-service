package de.logilutions.weatherservice.service;

import de.logilutions.weatherservice.model.Measurement;
import de.logilutions.weatherservice.model.Unit;
import de.logilutions.weatherservice.repository.MeasurementRepository;
import de.logilutions.weatherservice.repository.UnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MeasurementService {
    MeasurementRepository measurementRepository;
    UnitRepository unitRepository;

    public MeasurementService(MeasurementRepository measurementRepository, UnitRepository unitRepository) {
        this.measurementRepository = measurementRepository;
        this.unitRepository = unitRepository;
    }

    // Getter

    public List<Measurement> getAll() {
        return measurementRepository.findAll();
    }

    public List<Measurement> getAllBetween(LocalDateTime start, LocalDateTime end) {
        return measurementRepository.findMeasurementByTimestampBetweenOrderByTimestamp(start, end);
    }

    public List<Measurement> getAllBetweenByUnit(LocalDateTime start, LocalDateTime end, String key) {
        Unit unit = unitRepository.findUnitByKey(key);
        if (unit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no unit with key " + key + " found!");
        }
        return measurementRepository.findMeasurementByTimestampBetweenAndUnitOrderByTimestamp(start, end, unit);
    }

    public List<Measurement> getAllByUnit(String key) {
        Unit unit = unitRepository.findUnitByKey(key);
        if (unit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no unit with key " + key + " found!");
        }
        return measurementRepository.findMeasurementByUnitOrderByTimestamp(unit);
    }

    public Measurement getById(Long id) {
        Measurement measurement = measurementRepository.findMeasurementById(id);
        if (measurement == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no measurement with id " + id + " found!");
        } else {
            return measurement;
        }
    }



    public Measurement addMeasurement(Measurement measurement) {
        measurement.setId(null);
        return saveMeasurement(measurement);
    }

    public List<Measurement> addAll(List<Measurement> measurements) {
        for (Measurement measurement :
                measurements) {
            measurement.setId(null);
        }
        return measurementRepository.saveAll(measurements);
    }

    public Measurement saveMeasurement(Measurement measurement) {
        Unit unit = measurement.getUnit();
        if (unit == null) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "unit of measurement is missung");
        }
        Long unitId = measurement.getUnit().getId();
        String unitKey = measurement.getUnit().getKey();

        Unit newUnit = null;
        if (unitId != null) {
            newUnit = unitRepository.findUnitById(unitId);
        }
        if (unitKey != null) {
            newUnit = unitRepository.findUnitByKey(unitKey);
        }

        if (newUnit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no unit with given key/id found!");
        }
        measurement.setUnit(newUnit);
        return measurementRepository.save(measurement);
    }

    public void deleteMeasurement(Long id) {
        Measurement measurement = measurementRepository.findMeasurementById(id);
        if (measurement == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no measurement with id " + id + " found!");
        } else {
            measurementRepository.delete(measurement);
        }
    }
}
