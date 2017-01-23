package com.allstate.services;

import com.allstate.entities.User;
import com.allstate.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private IUserRepository repository;

    public User create(String userName, int balance){
        return this.repository.save(new User(userName, balance));
    }

    public Iterable<User> findAll(){
        return this.repository.findAll();
    }

}