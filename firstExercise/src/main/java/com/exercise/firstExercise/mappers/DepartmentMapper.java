package com.exercise.firstExercise.mappers;

import com.exercise.firstExercise.dto.DepartmentDto;
import com.exercise.firstExercise.models.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper {
    @Mapping(target = "title", source = "dto.title")
    Department convertDtoToEntity(DepartmentDto dto, Long id);

    @Mapping(target = "title", source = "entity.title")
    DepartmentDto convertEntityToDto(Department entity);
}
