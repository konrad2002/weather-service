package de.logilutions.weatherservice.repository;

import de.logilutions.weatherservice.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    Unit findUnitById(Long id);
    List<Unit> findAll();
    Unit findUnitByKey(String key);
}
