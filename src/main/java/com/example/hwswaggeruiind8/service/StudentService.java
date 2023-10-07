package com.example.hwswaggeruiind8.service;

import com.example.hwswaggeruiind8.entity.Faculty;
import com.example.hwswaggeruiind8.entity.Student;
import com.example.hwswaggeruiind8.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final Object object = new Object();

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

    public List<String> findAllStartFromA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("А"))
                .sorted()
                .collect(Collectors.toList());
    }

    public double avgAge() {
        return studentRepository.findAll().stream()
                .mapToDouble(i -> (double) i.getAge())
                .average()
                .orElseThrow(() -> new RuntimeException("Ошибка вычисления среднего возраста"));
    }

    public int calculate() {
        long start = System.currentTimeMillis();
        int result = Stream
                .iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        long finish = System.currentTimeMillis();
        logger.info("Calculate time: " + (finish - start));
        return result;
    }

    /*
    без parallel:
    Calculate time: 65
    Calculate time: 39
    Calculate time: 108
    Calculate time: 58

    c parallel:
    Calculate time: 335
    Calculate time: 109
    Calculate time: 49
    Calculate time: 153
    Calculate time: 217
    Calculate time: 176
    * */

    public void printStudentsName() {
        List<Student> students = studentRepository.findAll();
        printStudentName(students.get(0));
        printStudentName(students.get(1));

        new Thread(() -> {
            printStudentName(students.get(2));
            printStudentName(students.get(3));
        }).start();

        new Thread(() -> {
            printStudentName(students.get(4));
            printStudentName(students.get(5));
        }).start();
    }

    private void printStudentName(Student student) {
        System.out.println(Thread.currentThread() + " " + student);
    }

    public void printStudentsNameSync() {
        List<Student> students = studentRepository.findAll();
        printStudentName(students.get(0));
        printStudentName(students.get(1));

        new Thread(() -> {
            printStudentName(students.get(2));
            printStudentName(students.get(3));
        }).start();

        new Thread(() -> {
            printStudentName(students.get(4));
            printStudentName(students.get(5));
        }).start();
    }

    private void printStudentNameSync(Student student) {
        synchronized (object) {
            System.out.println(Thread.currentThread() + " " + student);
        }
    }
}
