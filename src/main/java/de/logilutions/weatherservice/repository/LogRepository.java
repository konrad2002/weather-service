package de.logilutions.weatherservice.repository;

import de.logilutions.weatherservice.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    Log findLogById(Long id);
}
