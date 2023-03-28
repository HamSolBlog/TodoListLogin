package com.example.demo.service;

import com.example.demo.entity.TodoListEntity;

import java.util.Map;

public interface TodoService {

    public Map<String,Object> findId(String USER_ID);

    public Map<String,Object> saveMember(TodoListEntity todoListEntity);

    public Map<String,Object> loginMember(TodoListEntity todoListEntity);

    public Map<String,Object> outMember(TodoListEntity todoListEntity);
}
