package com.example.demo.controllers;
/*
Zadanie 1: Student Controller
Spraw, aby każda z metod znajdujących się w StudentController zwracała ResponseEntity, przy czym:
Metody GET mają zwracać w swoim ciele obiekt(y) zwracane przez metodę, a status 200 jeśli zostaną znalezione.
Jeśli nie znaleziono studenta, to 404.
Metoda POST ma zwrócić status 201 jeśli się powiodą
Metody PUT i DELETE mają zwrócić status 200 jeśli się powiodą; status 404 jeśli podany student nie został odnaleziony
 */

import java.util.List;
import java.util.Optional;

import com.example.demo.handler.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.students.Student;
import com.example.demo.students.StudentRepository;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

//    @GetMapping("student/{id}")
//    public Optional<Student> getStudentById(@PathVariable Long id) {
//        return studentRepository.findById(id);
//    }

    @GetMapping("student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent())
            return new ResponseEntity<>(student.get(), HttpStatus.OK);  //200
        else {
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);  //404
            throw new StudentNotFoundException("Student not found");
        }

    }

//    @GetMapping("students/{firstName}")
//    public List<Student> getStudentsByFirstName(@PathVariable String firstName) {
//        return studentRepository.findByFirstName(firstName);
//    }

    @GetMapping("students/{firstName}")
    public ResponseEntity<List<Student>> getStudentsByFirstName(@PathVariable String firstName) throws StudentNotFoundException {
        List<Student> listOfStudents = studentRepository.findByFirstName(firstName);
        if (listOfStudents.isEmpty()) {
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);  //404
            throw new StudentNotFoundException("Students " + "with name " + firstName + " not found");
        } else {
            return new ResponseEntity<>(listOfStudents, HttpStatus.OK);  //200
        }

    }

//    @PostMapping("student")
//    public void createStudent(@RequestBody Student request) {
//        studentRepository.save(new Student(request.getFirstName(), request.getLastName()));
//    }

    @PostMapping("student")
    public ResponseEntity<Student> createStudent(@RequestBody Student request) {
        studentRepository.save(new Student(request.getFirstName(), request.getLastName()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PutMapping("student")
//    public void updateStudent(@RequestBody Student request) {
//        Optional<Student> student = studentRepository.findById(request.getId());
//        if (student.isPresent()) {
//            student.get().setFirstName(request.getFirstName());
//            student.get().setLastName(request.getLastName());
//            studentRepository.save(student.get());
//        }
//    }

    @PutMapping("student")
    public ResponseEntity<Student> updateStudent(@RequestBody Student request) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(request.getId());
        if (student.isPresent()) {
            student.get().setFirstName(request.getFirstName());
            student.get().setLastName(request.getLastName());
            studentRepository.save(student.get());

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new StudentNotFoundException("Student not found");
        }
    }

    @DeleteMapping("student/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            // return new ResponseEntity(HttpStatus.NOT_FOUND);
            throw new StudentNotFoundException("Student not found");
        }
    }
}