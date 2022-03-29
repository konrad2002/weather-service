package de.logilutions.weatherservice.service;

import de.logilutions.weatherservice.model.Log;
import de.logilutions.weatherservice.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<Log> getAll() {
        return logRepository.findAll();
    }

    public Log getById(Long id) {
        Log log = logRepository.findLogById(id);
        if (log == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no log with id " + id + " found!");
        } else {
            return log;
        }
    }


    public Log addLog(Log log) {
        log.setId(null);
        return saveLog(log);
    }

    public Log saveLog(Log log) {
        Log newLog = logRepository.save(log);
        return logRepository.getById(newLog.getId());
    }

    public void deleteLog(Long id) {
        Log log = getById(id);
        this.logRepository.delete(log);
    }
}
