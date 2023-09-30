package com.example.hwswaggeruiind8.service;

import com.example.hwswaggeruiind8.entity.Faculty;
import com.example.hwswaggeruiind8.entity.Student;
import com.example.hwswaggeruiind8.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(String name, int age) {
        logger.info("Был вызван метод add");
        Student newStudent = new Student(name, age);
        return studentRepository.save(newStudent);
    }

    public Student get(long id) {
        logger.info("Был вызван метод get");
        return studentRepository.findById(id).get();
    }

    public Student update(long id, String name, int age) {
        logger.info("Был вызван метод update");
        Student studentForUpdate = studentRepository.findById(id).get();
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        return studentRepository.save(studentForUpdate);
    }

    public Student delete(long id) {
        logger.info("Был вызван метод delete");
        Student studentForDelete = studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return studentForDelete;
    }

    public List<Student> getByAge(int age) {
        logger.info("Был вызван метод getByAge");
        return studentRepository.findAllByAge(age);
    }

    public List<Student> findByAgeBetween(int min, int max) {
        logger.info("Был вызван метод findByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getFaculty(Long studentId) {
        logger.info("Был вызван метод getFaculty");
        return studentRepository.findById(studentId).get().getFaculty();
    }

    public List<Student> findByFacultyId(long facultyId) {
        logger.info("Был вызван метод findByFacultyId");
        return studentRepository.findByFacultyId(facultyId);
    }
}
