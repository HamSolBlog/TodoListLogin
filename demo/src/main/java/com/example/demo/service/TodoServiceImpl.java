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

        log.info("Service findId 호출 완료! 파라미터 : " + id);

        Optional<TodoListEntity> todoListEntity = todoRepository.findById(id);
        log.info("todoListEntity : " + todoListEntity);

        Map<String,Object> todoMap = new HashMap<>();

        if (todoListEntity.isPresent())
        {
            log.info("데이터 확인 todoListEntity : " + todoListEntity);
            todoListEntityDatas = todoListEntity.get();
        }
        else
        {
                log.info("데이터 없음");
//                todoListEntityDatas = todoListEntity.orElse(null);
                todoMap.put("HTTPStatusCode",HttpStatus.NOT_FOUND);
                return todoMap;
        }

//        todoMap.put("user_id",todoListEntityDatas.getUser_id());
//        todoMap.put("user_password",todoListEntityDatas.getUser_password());
//        todoMap.put("user_email",todoListEntityDatas.getUser_email());
        todoMap.put("ID","이미 존재하는 ID");
        todoMap.put("HTTPStatusCode",HttpStatus.OK);

        log.info("Service findId 호출 종료! return : " + todoMap);

        return todoMap;
    }

    @Override
    public Map<String,Object> saveMember(TodoListEntity todoListEntity) {

        log.info("Service saveMember 호출 완료! Param: " + todoListEntity.getUser_id() +" , "+ todoListEntity.getUser_password() + " , " + todoListEntity.getUser_email());

        Map<String,Object> todoMap = new HashMap<>();

        try
        {
            todoRepository.save(todoListEntity);
            todoMap.put("HTTPStatusCode",HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.info("saveMember 에러발생!!");
            todoMap.put("HTTPStatusCode",HttpStatus.BAD_REQUEST);
            return todoMap;
        }

        log.info("Service saveMember 호출 종료!");
        return todoMap;
    }

    @Override
    public Map<String, Object> loginMember(TodoListEntity todoListEntity) {

        String id = todoListEntity.getUser_id();
        String password = todoListEntity.getUser_password();

        log.info("Service loginMember 호출 완료! Param: " + todoListEntity.getUser_id() +" , "+ todoListEntity.getUser_password());

        Optional<TodoListEntity> todoListLoginEntity = todoRepository.findById(todoListEntity.getUser_id());

        Map<String,Object> todoMap = new HashMap<>();


        if (todoListLoginEntity.isPresent())
        {
            log.info("데이터 확인 todoListLoginEntity : " + todoListLoginEntity);
            todoListEntityDatas = todoListLoginEntity.get();
        }
        else
        {
            log.info("Login 데이터 없음");
            todoMap.put("ID","NULL");
            todoMap.put("HTTPStatusCode",HttpStatus.NOT_FOUND);
            return todoMap;
        }

        if((todoListEntityDatas.getUser_id().equals(id)) && (todoListEntityDatas.getUser_password().equals(password)))
        {
            log.info("로그인 승인");
            todoMap.put("Login","승인");
            todoMap.put("user_id",id);
            todoMap.put("HTTPStatusCode",HttpStatus.OK);

            log.info("Service loginMember 호출 종료!");
            return todoMap;
        }
        else if ((todoListEntityDatas.getUser_id().equals(id)))
        {
            log.info("password 불일치");
            todoMap.put("Login","미승인");
            todoMap.put("password","Error");
            todoMap.put("HTTPStatusCode",HttpStatus.NOT_FOUND);

            log.info("Service loginMember 호출 종료!");
            return todoMap;
        }

        log.info("실패지점");
        todoMap.put("Login","미승인");
        todoMap.put("ID","Error");
        todoMap.put("password","Error");
        todoMap.put("HTTPStatusCode",HttpStatus.NOT_FOUND);

        log.info("Service loginMember 호출 종료!");
        return todoMap;
    }

    @Override
    public Map<String, Object> outMember(TodoListEntity todoListEntity) {
        String id = todoListEntity.getUser_id();
        String password = todoListEntity.getUser_password();

        log.info("Service outMember 호출 완료! Param: " + todoListEntity.getUser_id() +" , "+ todoListEntity.getUser_password());

        Optional<TodoListEntity> todoListLoginEntity = todoRepository.findById(todoListEntity.getUser_id());

        Map<String,Object> todoMap = new HashMap<>();

        if (todoListLoginEntity.isPresent())
        {
            log.info("데이터 확인 todoListLoginEntity : " + todoListLoginEntity);
            todoListEntityDatas = todoListLoginEntity.get();
        }
        else
        {
            log.info("회원 데이터 없음");
            todoMap.put("ID","NULL");
            todoMap.put("HTTPStatusCode",HttpStatus.NOT_FOUND);
            return todoMap;
        }

        if((todoListEntityDatas.getUser_id().equals(id)) && (todoListEntityDatas.getUser_password().equals(password)))
        {
            todoRepository.deleteById(todoListEntityDatas.getUser_id());
            log.info("탈퇴 승인");
            todoMap.put("회원 탈퇴","승인");
            todoMap.put("HTTPStatusCode",HttpStatus.OK);

            log.info("Service outMember 호출 종료!");
            return todoMap;
        }


        return null;
    }
}