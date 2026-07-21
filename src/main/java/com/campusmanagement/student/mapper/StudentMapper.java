package com.campusmanagement.student.mapper;

import com.campusmanagement.student.dto.StudentResponse;
import com.campusmanagement.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "email", source = "user.email")
    StudentResponse toResponse(Student student);

}