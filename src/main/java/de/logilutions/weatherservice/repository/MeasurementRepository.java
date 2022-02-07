package de.logilutions.weatherservice.repository;

import de.logilutions.weatherservice.model.Measurement;
import de.logilutions.weatherservice.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    Measurement findMeasurementById(Long id);
    List<Measurement> findAll();
    List<Measurement> findMeasurementByIdIsNotNullOrderByTimestampDesc();
    List<Measurement> findMeasurementByUnitOrderByTimestamp(Unit unit);
    List<Measurement> findMeasurementByUnitOrderByTimestampDesc(Unit unit);
    List<Measurement> findMeasurementByTimestampBetweenOrderByTimestamp(LocalDateTime start, LocalDateTime end);
    List<Measurement> findMeasurementByTimestampBetweenAndUnitOrderByTimestamp(LocalDateTime start, LocalDateTime end, Unit unit);
}
