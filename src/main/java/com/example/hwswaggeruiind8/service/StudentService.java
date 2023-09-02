package com.example.hwswaggeruiind8.service;

import com.example.hwswaggeruiind8.entity.Student;
import com.example.hwswaggeruiind8.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(String name, int age) {
        Student newStudent = new Student(name, age);
        return studentRepository.save(newStudent);
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
}
