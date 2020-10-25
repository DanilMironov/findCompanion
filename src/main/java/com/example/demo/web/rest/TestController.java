package com.example.demo.web.rest;

import com.example.demo.domain.TestTable;
import com.example.demo.service.TestTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //чтобы объявить класс рест-контроллером
@RequestMapping(path = "/api") // чтобы не прописывать везде путь с api
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private TestTableService testTableService;

    public TestController(TestTableService testTableService) {
        this.testTableService = testTableService;
    }

    @GetMapping(path = "/method/{name}") //чтобы в адремной строке вызвать метод с параметром Name
    public ResponseEntity<TestTable> method(final @PathVariable(value = "name") String name) {
        LOGGER.info("Received REST request with name = {}", name);
        TestTable testTable = testTableService.save(name);
        return ResponseEntity.ok(testTable);
    }
}
