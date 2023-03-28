package com.example.demo.entity;


import lombok.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "todo_list")
@Data
@Builder
@Repository
public class TodoListEntity {

    @Id
    @Column(name = "user_id")
    private String user_id;

    @Column(name = "user_password")
    private String user_password;

    @Column(name = "user_email")
    private String user_email;
}
