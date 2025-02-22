package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

	Employee findByUsername(String username);
}