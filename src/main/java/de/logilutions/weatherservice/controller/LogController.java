package de.logilutions.weatherservice.controller;

import de.logilutions.weatherservice.model.Log;
import de.logilutions.weatherservice.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("api/v1/log/")
public class LogController {
    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public List<Log> getAll() {
        return logService.getAll();
    }

    @GetMapping("{id}")
    public Log getById(@PathVariable Long id) {
        return logService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Log addLog(@RequestBody Log log) {
        return logService.addLog(log);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Log updateLog(@RequestBody Log log) {
        return logService.saveLog(log);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteLog(@PathVariable Long id) {
        logService.deleteLog(id);
    }
}
