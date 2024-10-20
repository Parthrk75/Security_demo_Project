package com.example.controller;

import com.example.entity.Employee;
import com.example.service.EmployeeService; // Adjust the package as per your structure

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee") // Base URL for employee endpoints
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;

    
    // Retrieve the authenticated employee's details
    @GetMapping("/profile") // Endpoint to get employee profile
    @PreAuthorize("hasRole('USER')") // Only accessible by USER role
    public ResponseEntity<Employee> getEmployeeProfile(Authentication authentication) {
        String username = authentication.getName(); // Get the username from the authentication object
        Employee employee = employeeService.getEmployeeByUsername(username); // Fetch employee details by username
        return ResponseEntity.ok(employee);
    }

    // Update the authenticated employee's details
    @PutMapping("/profile") // Endpoint to update employee profile
    @PreAuthorize("hasRole('USER')") // Only accessible by USER role
    public ResponseEntity<String> updateEmployeeProfile(@RequestBody Employee employee, Authentication authentication) {
        String username = authentication.getName(); // Get the username from the authentication object
        employeeService.updateEmployeeByUsername(username, employee); // Update employee details by username
        return ResponseEntity.ok("Employee profile updated successfully");
    }
}
