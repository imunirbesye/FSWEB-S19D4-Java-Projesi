package com.workintech.s19d4.repository;

import com.workintech.s19d4.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.email=:email")
    Optional<Employee> findByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.salary>:salary ORDER BY e.salary desc")
    List<Employee> findBySalary(double salary);

    @Query("SELECT e FROM Employee e ORDER BY e.lastName desc")
    List<Employee> findByOrder();

}
