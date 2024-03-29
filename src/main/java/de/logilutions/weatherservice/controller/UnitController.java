package de.logilutions.weatherservice.controller;

import de.logilutions.weatherservice.model.Unit;
import de.logilutions.weatherservice.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("api/v1/unit/")
public class UnitController {
    private UnitService unitService;

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public List<Unit> getAll() {
        return unitService.getAll();
    }

    @GetMapping("{id}")
    public Unit getById(@PathVariable Long id) {
        return unitService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Unit postUnit(@RequestBody Unit unit) {
        return unitService.addUnit(unit);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Unit updateUnit(@RequestBody Unit unit) {
        return unitService.saveUnit(unit);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);
    }
}
