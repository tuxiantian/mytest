package com.tuxt.mytest.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "dto.grades",target = "className")
    Student dtoToBo(StudentDTO dto);
}
