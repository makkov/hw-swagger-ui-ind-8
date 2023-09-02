package com.example.hwswaggeruiind8.repository;

import com.example.hwswaggeruiind8.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAge(Integer age);

    List<Student> findByAgeBetween(Integer min, Integer max);
}
