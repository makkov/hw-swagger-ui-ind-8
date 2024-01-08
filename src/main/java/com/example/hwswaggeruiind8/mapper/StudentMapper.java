package com.example.hwswaggeruiind8.mapper;

import com.example.hwswaggeruiind8.dto.AggregateResponseDto;
import com.example.hwswaggeruiind8.dto.FacultyDto;
import com.example.hwswaggeruiind8.dto.StudentDto;
import com.example.hwswaggeruiind8.dto.StudentResponseDto;
import com.example.hwswaggeruiind8.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StudentMapper {

    StudentResponseDto studentToStudentResponseDto(Student student);

    @Mapping(target = "faculty", source = "facultyDto")
    @Mapping(target = "student", source = "studentDto")
    AggregateResponseDto facultyDtoAndStudentDtoToAggregateResponseDto(FacultyDto facultyDto, StudentDto studentDto);
}
