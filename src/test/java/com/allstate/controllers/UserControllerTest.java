package com.allstate.controllers;

import com.allstate.entities.User;
import com.allstate.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    public void shouldCreateUser() throws Exception {
        User user = new User("Apaka", 200);
        user.setId(1);
        when(this.service.create("Apaka",200)).thenReturn(user);

        MockHttpServletRequestBuilder request = post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                            "\t\"userName\" : \"Apaka\",\n" +
                            "\t\"balance\" : 200\n" +
                            "}");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.userName",is(user.getUserName())))
                .andExpect(jsonPath("$.balance",is(user.getBalance())));

    }

    @Test
    public void shouldGetAllUsers() throws Exception {

        List<User> lst =  new ArrayList<>();
        lst.add(new User("Apaka",200));
        lst.add(new User("Dikshi",300));
        when(this.service.findAll()).thenReturn(lst);

        MockHttpServletRequestBuilder request = get("/users/");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(2)))
                .andExpect(jsonPath("$[0].userName",is("Apaka")))
                .andExpect(jsonPath("$[0].balance",is(200)))
                .andExpect(jsonPath("$[1].userName",is("Dikshi")))
                .andExpect(jsonPath("$[1].balance",is(300)));
    }
}