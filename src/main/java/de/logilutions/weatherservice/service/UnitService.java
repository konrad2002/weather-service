package de.logilutions.weatherservice.service;

import de.logilutions.weatherservice.model.Unit;
import de.logilutions.weatherservice.repository.UnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class UnitService {
    UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> getAll() {
        return unitRepository.findAll();
    }

    public Unit getById(Long id) {
        Unit unit = unitRepository.findUnitById(id);
        if (unit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no unit with id " + id + " found!");
        } else {
            return unit;
        }
    }


    public Unit addUnit(Unit unit) {
        unit.setId(null);
        return saveUnit(unit);
    }

    public Unit saveUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    public void deleteUnit(Long id) {
        Unit unit = unitRepository.findUnitById(id);
        if (unit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no unit with id " + id + " found!");
        } else {
            unitRepository.delete(unit);
        }
    }
}
