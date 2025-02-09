package com.exercise.firstExercise.repositories;

import com.exercise.firstExercise.models.Department;
import com.exercise.firstExercise.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeesByFirstName(String firstName);
    List<Employee> findEmployeesByLastName(String lastName);
    Optional<Employee> findEmployeeByBadgeNumber(Integer badgeNumber);
    List<Employee> findEmployeesByDepartment(Department department);
    List<Employee> findEmployeesByIsManagerTrue();
}
