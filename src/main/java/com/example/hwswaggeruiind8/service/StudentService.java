package com.example.hwswaggeruiind8.service;

import com.example.hwswaggeruiind8.dto.StudentResponseDto;
import com.example.hwswaggeruiind8.entity.Faculty;
import com.example.hwswaggeruiind8.entity.Student;
import com.example.hwswaggeruiind8.mapper.StudentMapper;
import com.example.hwswaggeruiind8.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto add(String name, int age) {
        Student newStudent = new Student(name, age);
        return studentMapper.studentToStudentResponseDto(newStudent);
    }

    public Student get(long id) {
        return studentRepository.findById(id).get();
    }

    public Student update(long id, String name, int age) {
        Student studentForUpdate = studentRepository.findById(id).get();
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        return studentRepository.save(studentForUpdate);
    }

    public Student delete(long id) {
        Student studentForDelete = studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return studentForDelete;
    }

    public List<Student> getByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public List<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getFaculty(Long studentId) {
        return studentRepository.findById(studentId).get().getFaculty();
    }

    public List<Student> findByFacultyId(long facultyId) {
        return studentRepository.findByFacultyId(facultyId);
    }
}
