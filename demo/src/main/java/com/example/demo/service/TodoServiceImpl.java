package com.example.demo.service;

import com.example.demo.entity.TodoListEntity;
import com.example.demo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService{

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoListEntity todoListEntityDatas;

    @Override
    public Map<String, Object> findId(String id) {

        log.info("Service 호출 완료! 파라미터 : " + id);

        Optional<TodoListEntity> todoListEntity = todoRepository.findById(id);
        log.info("todoListEntity : " + todoListEntity);

        Map<String,Object> todoMap = new HashMap<>();

        if (todoListEntity.isPresent())
        {
            log.info("데이터 확인 todoListEntityDatas : " + todoListEntityDatas);
            todoListEntityDatas = todoListEntity.get();
        }
        else
        {
                log.info("데이터 없음");
//                todoListEntityDatas = todoListEntity.orElse(null);
                todoMap.put("HTTPStatusCode",HttpStatus.NOT_FOUND);
                return todoMap;
        }

        todoMap.put("user_id",todoListEntityDatas.getUser_id());
        todoMap.put("user_password",todoListEntityDatas.getUser_password());
        todoMap.put("user_email",todoListEntityDatas.getUser_email());
        todoMap.put("HTTPStatusCode",HttpStatus.OK);

        log.info("Service 호출 종료! return : " + todoMap);

        return todoMap;
    }
}