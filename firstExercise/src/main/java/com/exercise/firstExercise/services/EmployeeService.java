package com.exercise.firstExercise.services;

import com.exercise.firstExercise.dto.EmployeeDto;
import com.exercise.firstExercise.mappers.EmployeeMapper;
import com.exercise.firstExercise.models.Department;
import com.exercise.firstExercise.models.Employee;
import com.exercise.firstExercise.repositories.DepartmentRepo;
import com.exercise.firstExercise.repositories.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final DepartmentRepo departmentRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public List<Employee> getAllEmployeesManagers(){
        return employeeRepo.findEmployeesByIsManagerTrue();
    }

    public List<Employee> getEmployeesByFirstName(String firstName) throws Exception{
        List<Employee> employees = employeeRepo.findEmployeesByFirstName(firstName);
        if(employees.isEmpty())
            throw new Exception("No employees were found with the first name " + firstName);
        else
            return employees;
    }

    public List<Employee> getEmployeesByLastName(String lastName) throws Exception{
        List<Employee> employees = employeeRepo.findEmployeesByLastName(lastName);
        if(employees.isEmpty())
            throw new Exception("No employees were found with the last name " + lastName);
        else
            return employees;
    }

    public List<Employee> getEmployeesByDepartmentTitle(String title) throws Exception{
        Optional<Department> department = departmentRepo.findDepartmentByTitle(title);
        if(department.isPresent()) {
            List<Employee> employeesInDep = employeeRepo.findEmployeesByDepartment(department.get());
            if(employeesInDep.isEmpty())
            {
                throw new Exception("There aren't any employees in department " + title);
            }
            else
                return employeesInDep;
        }
        else
            throw new Exception("No department was found with title " + title);
    }

    public Employee getEmployeeByBadgeNumber(Integer badgeNum) throws Exception{
        return employeeRepo.findEmployeeByBadgeNumber(badgeNum)
                .orElseThrow(() -> new Exception("No employee was found with the badge number: " + badgeNum));
    }

    @Transactional
    public Employee saveEmployee(EmployeeDto dto){
        Integer badgeNum = dto.badgeNum();
        Optional<Employee> existingEmployee = employeeRepo.findEmployeeByBadgeNumber(badgeNum);

        if (existingEmployee.isPresent()) {
            Long id = existingEmployee.get().getId();
            log.info("You are updating an employee with badge number {}", id);
            return employeeRepo.saveAndFlush(employeeMapper.convertDtoToEntity(dto, id));
        } else {
            log.info("You are creating a new employee");
            return employeeRepo.saveAndFlush(employeeMapper.convertDtoToEntity(dto, null));
        }
    }

    @Transactional
    public void deleteEmployeeByBadgeNum(Integer badgeNum) throws Exception{
        Optional<Employee> EmployeeToBeDeleted = employeeRepo.findEmployeeByBadgeNumber(badgeNum);
            if(EmployeeToBeDeleted.isPresent())
                {
                    employeeRepo.deleteById(EmployeeToBeDeleted.get().getId());
                }
            else
                throw new Exception("No employee was found with the badge number: " + badgeNum);
    }
}
