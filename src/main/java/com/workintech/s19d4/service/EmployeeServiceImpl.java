package com.workintech.s19d4.service;

import com.workintech.s19d4.entity.Employee;
import com.workintech.s19d4.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent())
            return employeeOptional.get();

        return null;
    }

    @Override
    public Employee save(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employee.getEmail());
        if(employeeOptional.isPresent())
            return null;

        return employeeRepository.save(employee);
    }

    @Override
    public Employee delete(long id) {
        Employee employee = findById(id);
        employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public Employee findByEmail(String email) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(email);
        if(employeeOptional.isPresent())
            return employeeOptional.get();

        return null;
    }

    @Override
    public List<Employee> findBySalary(double minSalary) {
        return employeeRepository.findBySalary(minSalary);
    }

    @Override
    public List<Employee> findByOrder() {
        return employeeRepository.findByOrder();
    }
}
