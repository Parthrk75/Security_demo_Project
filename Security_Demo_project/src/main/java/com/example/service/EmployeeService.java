package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(long id) {
		return employeeRepository.findById(id).orElse(null);
	}

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void updateEmployee(Long id, Employee employee) {
        // Check if employee exists before updating
        if (employeeRepository.existsById(id)) {
            employee.setId(id); // Ensure the ID remains the same
            employeeRepository.save(employee);
        } else {
            // Handle not found case
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    public void deleteEmployee(Long id) {
        // Check if employee exists before deleting
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            // Handle not found case
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

	public Employee getEmployeeByUsername(String username) {
		// TODO Auto-generated method stub
		return employeeRepository.findByUsername(username);
	}

	public void updateEmployeeByUsername(String username, Employee employee) {
		// TODO Auto-generated method stub
		Employee existingEmployee = getEmployeeByUsername(username);
        if (existingEmployee != null) {
            employee.setId(existingEmployee.getId()); // Ensure the ID remains the same
            employeeRepository.save(employee);
        } else {
            throw new RuntimeException("Employee not found with username: " + username);
        }
	}
	
}
