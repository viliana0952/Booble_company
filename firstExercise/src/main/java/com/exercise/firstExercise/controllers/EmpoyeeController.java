package com.exercise.firstExercise.controllers;

import com.exercise.firstExercise.dto.EmployeeDto;
import com.exercise.firstExercise.models.Employee;
import com.exercise.firstExercise.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmpoyeeController {
    private final EmployeeService employeeService;

    @GetMapping("/fetch/employees")
    public List<Employee> fetchEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/fetch/employees/managers")
    public List<Employee> fetchEmployeesManagers(){
        return employeeService.getAllEmployeesManagers();
    }

    @GetMapping("/fetch/employees/{firstName}")
    public List<Employee> fetchEmployeesByFirstName(@PathVariable String firstName) throws Exception{
        return employeeService.getEmployeesByFirstName(firstName);
    }

    @GetMapping("/fetch/employees/{lastName}")
    public List<Employee> fetchEmployeesByLastName(@PathVariable String lastName) throws Exception {
        return employeeService.getEmployeesByLastName(lastName);
    }

    @GetMapping("/fetch/employees/{departmentTitle}")
    public List<Employee> fetchEmployeesByDepartmentTitle(@PathVariable String departmentTitle) throws Exception{
        return employeeService.getEmployeesByDepartmentTitle(departmentTitle);
    }

    @GetMapping("/fetch/employees/{badgeNumber}")
    public Employee fetchEmployeeByBadgeNumber(@PathVariable Integer badgeNumber) throws Exception{
        return employeeService.getEmployeeByBadgeNumber(badgeNumber);
    }

    @PostMapping("/save/employees")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto dto) {
        Employee savedEmployee = employeeService.saveEmployee(dto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update/employees")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto dto) {
        Employee updatedEmployee = employeeService.saveEmployee(dto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/employees/{badgeNumber}")
    public ResponseEntity<?> deleteEmployeeByBadgeNumber(@PathVariable Integer badgeNumber) throws Exception {
        employeeService.deleteEmployeeByBadgeNum(badgeNumber);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
