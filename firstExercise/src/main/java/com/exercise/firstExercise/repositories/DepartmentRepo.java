package com.exercise.firstExercise.repositories;

import com.exercise.firstExercise.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Optional<Department> findDepartmentByTitle(String title);
}
