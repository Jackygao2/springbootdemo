package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.common.ResultGenerator;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api")
public class RestController {
    final
    UserDao userDao;

    public RestController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<User> getUser(@PathVariable("id") Integer id) {
        if (id == null || id < 1) {
            return ResultGenerator.genFailResult("ID can't be null");
        }
        User user = userDao.getUserById(id);
        if (user == null) {
            return ResultGenerator.genFailResult("User not found");
        }
        return ResultGenerator.genSuccessResult(user);
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public ModelAndView listAll() {
        List<User> users = userDao.findAllUsers();
        return new ModelAndView("listAll", "users", users);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<User>> queryAll() {
        List<User> users = userDao.findAllUsers();
        return ResultGenerator.genSuccessResult(users);
    }

    @GetMapping("/addUser")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userDao.insertUser(user);
        return "redirect:/ListAll";
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public Result<Boolean> update(@RequestBody User tempUser) {

        if (tempUser.getId() == null || tempUser.getId() < 1 || StringUtils.isEmpty(tempUser.getName()) || StringUtils.isEmpty(tempUser.getPassword())) {
            return ResultGenerator.genFailResult("no parameter");
        }
        User user = userDao.getUserById(tempUser.getId());
        if (user == null) {
            return ResultGenerator.genFailResult("parameter not exist");
        }
        user.setName(tempUser.getName());
        user.setPassword(tempUser.getPassword());
        return ResultGenerator.genSuccessResult(userDao.updUser(user) > 0);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Boolean> delete(@PathVariable("id") Integer id) {
        if (id == null || id < 1) {
            return ResultGenerator.genFailResult("parameter can't be null'");
        }
        return ResultGenerator.genSuccessResult(userDao.delUser(id) > 0);
    }

}
