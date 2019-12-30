package com.example.demo.students;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByFirstName(String firstName);
    List<Student> findByLastName(String lastName);
}
