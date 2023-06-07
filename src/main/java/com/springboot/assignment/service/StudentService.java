package com.springboot.assignment.service;

import com.springboot.assignment.dto.StudentDTO;
import com.springboot.assignment.entity.Student;

import java.util.List;


public interface StudentService {

    List<Student> findByUniversityId(int id);
    List<Student> findAll();

    Student findById(int theId);

    void save(StudentDTO studentDTO);

    void deleteById(int theId);
}
