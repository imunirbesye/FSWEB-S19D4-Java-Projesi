package com.workintech.s19d4.repository;

import com.workintech.s19d4.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EmployeeRepositoryTest {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @BeforeEach
    void setUp() {
        Employee employeeMunir = new Employee();
        employeeMunir.setFirstName("Münir");
        employeeMunir.setLastName("BESYE");
        employeeMunir.setEmail("munir@gmail.com");
        employeeMunir.setSalary(53000d);

        Employee employeeArda = new Employee();
        employeeArda.setFirstName("Arda");
        employeeArda.setLastName("BESYE");
        employeeArda.setEmail("arda@gmail.com");
        employeeArda.setSalary(50000d);

        Employee employeeAli = new Employee();
        employeeAli.setFirstName("Ali");
        employeeAli.setLastName("BESYE");
        employeeAli.setEmail("ali@gmail.com");
        employeeAli.setSalary(50000d);

        List<Employee> employees = new ArrayList<>();
        employees.add(employeeMunir);
        employees.add(employeeArda);
        employees.add(employeeAli);

        employeeRepository.saveAll(employees);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @Test
    void findByEmail() {
        String nonExistingEmail = "munir1@gmail.com";
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(nonExistingEmail);
        assertEquals(Optional.empty(), employeeOptional);

        String existingEmail = "munir@gmail.com";
        Optional<Employee> existingOptional = employeeRepository.findByEmail(existingEmail);
        assertNotNull(existingOptional.get());
        assertEquals("Münir", existingOptional.get().getFirstName());
    }

    @Test
    void findBySalary() {
        List<Employee> employees = employeeRepository.findBySalary(49000);
        assertEquals(3, employees.size());
    }

    @Test
    void findByOrder() {
        List<Employee> employees = employeeRepository.findByOrder();
        assertEquals(3, employees.size());
        assertEquals("BESYE", employees.get(0).getLastName());
    }

}