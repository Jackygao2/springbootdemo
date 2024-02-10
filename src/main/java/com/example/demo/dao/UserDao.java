package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /**
     * 返回数据列表
     *
     * @return
     */
    List<User> findAllUsers();

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);

    User getUserByNameAndPassword(String name, String password);

    /**
     * 添加
     *
     * @param User
     * @return
     */
    int insertUser(User User);

    /**
     * 修改
     *
     * @param User
     * @return
     */
    int updUser(User User);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delUser(Integer id);
}
