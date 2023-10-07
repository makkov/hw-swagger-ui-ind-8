package com.example.hwswaggeruiind8.controller;

import com.example.hwswaggeruiind8.entity.Faculty;
import com.example.hwswaggeruiind8.entity.Student;
import com.example.hwswaggeruiind8.service.AvatarService;
import com.example.hwswaggeruiind8.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.add(student.getName(), student.getAge());
    }

    @GetMapping
    public Student get(@RequestParam long id) {
        return studentService.get(id);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return studentService.update(student.getId(), student.getName(), student.getAge());
    }

    @DeleteMapping
    public Student delete(@RequestParam long id) {
        return studentService.delete(id);
    }

    @GetMapping("/by-age")
    public List<Student> getByAge(@RequestParam int age) {
        return studentService.getByAge(age);
    }

    @GetMapping("/by-age-between")
    public List<Student> getByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }

    @GetMapping("/faculty-by-id")
    public Faculty getFaculty(@RequestParam long id) {
        return studentService.getFaculty(id);
    }

    @GetMapping("/name/start-from-a")
    public List<String> startFromA() {
        return studentService.findAllStartFromA();
    }

    @GetMapping("/avg-age")
    public double avgAge() {
        return studentService.avgAge();
    }

    @GetMapping("/calculate")
    public int calculate() {
        return studentService.calculate();
    }
}
