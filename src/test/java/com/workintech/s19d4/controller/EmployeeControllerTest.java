package com.workintech.s19d4.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.s19d4.entity.Employee;
import com.workintech.s19d4.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void save() throws Exception {
        Employee employee = new Employee();
        employee.setId(1l);
        employee.setFirstName("munir");
        employee.setLastName("test");
        employee.setSalary(15000d);

        when(employeeService.save(employee)).thenReturn(employee);

        mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").exists());

    }

    private static String asJsonString(Employee employee) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(employee);
    }
}
