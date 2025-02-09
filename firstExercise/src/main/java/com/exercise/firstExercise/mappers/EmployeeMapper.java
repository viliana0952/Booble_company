package com.exercise.firstExercise.mappers;

import com.exercise.firstExercise.dto.EmployeeDto;
import com.exercise.firstExercise.models.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {
    @Mapping(target = "badgeNum", source = "dto.badgeNum")
    @Mapping(target = "firstName", source = "dto.firstName")
    @Mapping(target = "lastName", source = "dto.lastName")
    @Mapping(target = "isManager", source = "dto.isManager")
    Employee convertDtoToEntity(EmployeeDto dto, Long id);

    @Mapping(target = "badgeNum", source = "entity.badgeNum")
    @Mapping(target = "firstName", source = "entity.firstName")
    @Mapping(target = "lastName", source = "entity.lastName")
    @Mapping(target = "isManager", source = "entity.isManager")
    EmployeeDto convertEntityToDto(Employee entity);
}
