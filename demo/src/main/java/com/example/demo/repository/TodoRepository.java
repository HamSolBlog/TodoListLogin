package com.example.demo.repository;

import com.example.demo.entity.TodoListEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends CrudRepository<TodoListEntity,String>{

    @Override
    Optional<TodoListEntity> findById(String user_id);

    @Override
    <S extends TodoListEntity> S save(S entity);

    @Override
    void deleteById(String user_id);

}
