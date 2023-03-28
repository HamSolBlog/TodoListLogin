package com.example.demo.controller;

import com.example.demo.repository.TodoRepository;
import com.example.demo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TodoController {
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoService todoService;

    @GetMapping("/idMapping")
    public Map<String, Object> findID(@RequestParam("id") String id){

        log.info("Controller 호출 완료! 파라미터 : " + id); // default

        Map<String,Object> testMap = todoService.findId(id);

        log.info("Controller 호출 종료! 파라미터 : " + testMap); // default
        return testMap;
    }
}
