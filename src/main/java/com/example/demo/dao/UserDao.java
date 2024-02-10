package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    List<User> findAllUsers();


    User getUserById(Integer id);

    User getUserByNameAndPassword(String name, String password);


    int insertUser(User user);

    int updUser(User user);


    int delUser(Integer id);
}
