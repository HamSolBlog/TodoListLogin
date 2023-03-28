package com.example.demo.controller;

import com.example.demo.entity.TodoListEntity;
import com.example.demo.repository.TodoRepository;
import com.example.demo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/selectID")
    public Map<String, Object> findID(@RequestParam("id") String id){

        log.info("Controller 호출 완료! 파라미터 : " + id); // default

        Map<String,Object> testMap = todoService.findId(id);

        log.info("Controller 호출 종료! 파라미터 : " + testMap); // default
        return testMap;
    }

    @PostMapping("/saveMember")
    public Map<String, Object> saveMember(@RequestBody TodoListEntity todoListEntity){

        log.info("Controller 호출 완료! 파라미터 : " + todoListEntity.getUser_id() + " , " + todoListEntity.getUser_password() + " , " + todoListEntity.getUser_email()); // default

        Map<String,Object> testMap = todoService.saveMember(todoListEntity);

        log.info("Controller 호출 종료! 파라미터 : " + testMap); // default
        return testMap;
    }
    @GetMapping("/loginMember")
    public Map<String, Object> loginMember(@RequestBody TodoListEntity todoListEntity){

        log.info("Controller 호출 완료! 파라미터 : " + todoListEntity.getUser_id() + " , " + todoListEntity.getUser_password() + " , " + todoListEntity.getUser_email());

        Map<String,Object> testMap = todoService.loginMember(todoListEntity);

        log.info("Controller 호출 종료! 파라미터 : " + testMap); // default
        return testMap;
    }

    @DeleteMapping("/outMember")
    public Map<String, Object> outMember(@RequestBody TodoListEntity todoListEntity){

        log.info("Controller 호출 완료! 파라미터 : " + todoListEntity.getUser_id() + " , " + todoListEntity.getUser_password() + " , " + todoListEntity.getUser_email());

        Map<String,Object> testMap = todoService.outMember(todoListEntity);

        log.info("Controller 호출 종료! 파라미터 : " + testMap); // default
        return testMap;
    }
}
