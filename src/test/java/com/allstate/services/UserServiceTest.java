package com.allstate.services;

import com.allstate.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import javax.validation.*;

import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void shouldCreateUser() throws Exception {
        User u = this.service.create("Apaka",200);
        assertEquals(u.getUserName() , "Apaka");
        assertEquals(u.getBalance() , 200);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldThrowExceptionONDuplicateUser() throws Exception {
        this.service.create("Apaka",200);
        this.service.create("Apaka",200);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldThrowExceptionONCreateUserNameNull() throws Exception {
        this.service.create(null,0);
    }


    @Test(expected = ConstraintViolationException.class)
    public void shouldThrowExceptionONCreateUserNameLengthLessThanLimit() throws Exception {
        this.service.create("an",0);
    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        this.service.create("Apaka",200);
        this.service.create("Dikshi",300);
        Iterator<User> iterator = this.service.findAll().iterator();

        User obj = iterator.next();
        assertEquals("Apaka",obj.getUserName());
        assertEquals(200,obj.getBalance());

        obj = iterator.next();
        assertEquals("Dikshi",obj.getUserName());
        assertEquals(300,obj.getBalance());
    }
}