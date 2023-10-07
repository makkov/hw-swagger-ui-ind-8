package com.example.hwswaggeruiind8.service;

import com.example.hwswaggeruiind8.entity.Faculty;
import com.example.hwswaggeruiind8.entity.Student;
import com.example.hwswaggeruiind8.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentService studentService;

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository, StudentService studentService) {
        this.facultyRepository = facultyRepository;
        this.studentService = studentService;
    }

    public Faculty add(String name, String color) {
        logger.info("Был вызван метод add");
        Faculty newFaculty = new Faculty(name, color);
        return facultyRepository.save(newFaculty);
    }

    public Faculty get(long id) {
        logger.info("Был вызван метод get");
        return facultyRepository.findById(id).get();
    }

    public Faculty update(long id, String name, String color) {
        logger.info("Был вызван метод update");
        Faculty facultyForUpdate = facultyRepository.findById(id).get();
        facultyForUpdate.setName(name);
        facultyForUpdate.setColor(color);
        return facultyRepository.save(facultyForUpdate);
    }

    public Faculty delete(long id) {
        logger.info("Был вызван метод delete");
        Faculty facultyForDelete = facultyRepository.findById(id).get();
        facultyRepository.deleteById(id);
        return facultyForDelete;
    }

    public List<Faculty> getByColor(String color) {
        logger.info("Был вызван метод getByColor");
        return facultyRepository.findAllByColor(color);
    }

    public List<Faculty> getByColorOrName(String param) {
        logger.info("Был вызван метод getByColorOrName");
        return facultyRepository.findByColorContainsIgnoreCaseOrNameContainsIgnoreCase(param, param);
    }

    public List<Student> getStudents(Long id) {
        logger.info("Был вызван метод getStudents");
        return studentService.findByFacultyId(id);
    }

    public String longestName() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                //Задаем компаратор по длние слова
                .sorted((x, y) -> y.length() - x.length())
                .collect(Collectors.toList())
                .get(0);
    }
}
