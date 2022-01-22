package de.logilutions.weatherservice.repository;

import de.logilutions.weatherservice.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    Test findTestById(Long id);
    List<Test> findAll();
}
