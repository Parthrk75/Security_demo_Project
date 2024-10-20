package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.service.EmployeeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/getallemployee")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> employees =  employeeService.getAllEmployee();
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/getemployeebyid/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
        return ResponseEntity.ok("Employee data submitted successfully");
	}
	
	@PutMapping("/employees/{id}") // Endpoint to update an employee
    @PreAuthorize("hasRole('ADMIN')") // Only accessible by ADMIN role
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok("Employee data updated successfully");
    }

    // Delete an employee
    @DeleteMapping("/employees/{id}") // Endpoint to delete an employee
    @PreAuthorize("hasRole('ADMIN')") // Only accessible by ADMIN role
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee data deleted successfully");
    }
}
