package de.logilutions.weatherservice.service;

import de.logilutions.weatherservice.model.Test;
import de.logilutions.weatherservice.repository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class TestService {
    TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getTests() {
        return testRepository.findAll();
    }

    public Test getTestById(Long id) {
        Test test = testRepository.findTestById(id);
        if (test == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no test with id " + id + "found.");
        } else {
            return test;
        }
    }

    public Test addTest(Test test) {
        test.setId(null);
        return saveTest(test);
    }

    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    public void deleteTest(Long id) {
        Test test = testRepository.findTestById(id);
        if (test == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no test with id " + id + "found.");
        } else {
            testRepository.delete(test);
        }
    }
}
