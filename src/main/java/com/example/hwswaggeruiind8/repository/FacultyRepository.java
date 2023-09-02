package com.example.hwswaggeruiind8.repository;

import com.example.hwswaggeruiind8.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findAllByColor(String color);

       List<Faculty> findByColorContainsIgnoreCaseOrNameContainsIgnoreCase(String color, String name);
}
