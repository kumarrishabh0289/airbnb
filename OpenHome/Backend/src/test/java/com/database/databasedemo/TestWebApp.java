package com.database.databasedemo;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.database.databasedemo.controller.PersonResource;
import com.database.databasedemo.entity.Person;
import com.database.databasedemo.repository.PersonJPARepo;
import com.database.databasedemo.repository.PersonSpringDataRepo;
import com.database.databasedemo.repository.PropertyRepo;
import com.database.databasedemo.service.PropertyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class TestWebApp  {

//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    public void testEmployee() throws Exception {
////        mockMvc.perform(get("/employee")).andExpect(status().isOk())
////                .andExpect(content().contentType("application/json;charset=UTF-8"))
////                .andExpect(jsonPath("$.name").value("emp1")).andExpect(jsonPath("$.designation").value("manager"))
////                .andExpect(jsonPath("$.empId").value("1")).andExpect(jsonPath("$.salary").value(3000));
//
//    }

    @InjectMocks
    PersonResource personResource;

    @Mock
    PersonJPARepo personJPARepo;

    @Mock
    private PersonSpringDataRepo repo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    private PersonJPARepo ppersonJPARepo = Mockito.mock(PersonJPARepo.class);

    @Test
    public void getEmployeeByIdTest()
    {
       // when(personJPARepo.findById(4)).thenReturn(person);

        Person person = new Person("Lucky", "laxmikantpandhare@gmail.com");
        Person savedPerson;
        savedPerson = repo.save(person);

        Person emp = ppersonJPARepo.findById(4);
        System.out.println(emp);
        assertEquals("Rishabh", emp.getName());
    }

}