package com.example.hwswaggeruiind8.controller;

import com.example.hwswaggeruiind8.model.Faculty;
import com.example.hwswaggeruiind8.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty create(@RequestBody Faculty faculty) {
        return facultyService.add(faculty.getName(), faculty.getColor());
    }

    @GetMapping
    public Faculty get(@RequestParam long id) {
        return facultyService.get(id);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return facultyService.update(faculty.getId(), faculty.getName(), faculty.getColor());
    }

    @DeleteMapping
    public Faculty delete(@RequestParam long id) {
        return facultyService.delete(id);
    }

    @GetMapping("/by-color")
    public List<Faculty> getByColor(@RequestParam String color) {
        return facultyService.getByColor(color);
    }
}
