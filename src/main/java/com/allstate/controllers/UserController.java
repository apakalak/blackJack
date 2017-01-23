package com.allstate.controllers;

import com.allstate.entities.User;
import com.allstate.services.UserService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value={"/",""} ,method = RequestMethod.POST)
    public User create(@RequestBody Map<String, String> user){
        return this.service.create(user.get("userName"), Integer.parseInt(user.get("balance")));
    }

    @RequestMapping(value={"/",""})
    public Iterable<User> findAll(){
        return  this.service.findAll();
    }

}
