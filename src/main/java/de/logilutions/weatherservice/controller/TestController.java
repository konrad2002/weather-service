package de.logilutions.weatherservice.controller;

import de.logilutions.weatherservice.model.Test;
import de.logilutions.weatherservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("api/v1/test/")
public class TestController {
    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public List<Test> getTests() {
        return testService.getTests();
    }

    @GetMapping("{id}")
    public Test getTest(@PathVariable Long id) {
        return testService.getTestById(id);
    }

    @PutMapping
    public Test putTest(@RequestBody Test test) {
        return testService.saveTest(test);
    }

    @PostMapping
    public Test postTest(@RequestBody Test test) {
        return testService.addTest(test);
    }

    @DeleteMapping("{id}")
    public void deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
    }
}
