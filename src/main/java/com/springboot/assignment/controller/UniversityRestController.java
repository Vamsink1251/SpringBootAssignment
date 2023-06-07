package com.springboot.assignment.controller;


import com.springboot.assignment.dto.StudentDTO;
import com.springboot.assignment.dto.UniversityDTO;
import com.springboot.assignment.entity.Student;
import com.springboot.assignment.entity.University;
import com.springboot.assignment.exception.StudentNotFoundException;
import com.springboot.assignment.exception.UniversityNotFoundException;
import com.springboot.assignment.service.StudentService;
import com.springboot.assignment.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UniversityRestController {
    @Autowired
    private UniversityService universityService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/universities")
    public List<University> getUniversities(){
        return universityService.findAll();
    }

    @GetMapping("/universities/{universityId}")
    public University getUniversityById(@PathVariable int universityId){
        University university = universityService.findById(universityId);

        if(university == null){
            throw new UniversityNotFoundException("The University not found : "+universityId);
        }
        return university;
    }

    @PostMapping("/universities")
    public UniversityDTO addUniversity(@RequestBody UniversityDTO universityDTO){
        universityDTO.setId(0);
        universityService.save(universityDTO);

        return universityDTO;
    }

    @PutMapping("/universities")
    public UniversityDTO updateUniversity(@RequestBody UniversityDTO universityDTO){
        universityService.save(universityDTO);
        return universityDTO;
    }

    @DeleteMapping("universities/{universityId}")
    public String deleteUniversity(@PathVariable int universityId){
        if(universityService.findById(universityId) == null){
            throw new UniversityNotFoundException("university does not exist - " + universityId);
        }
        universityService.deleteById(universityId);

        return "Deleted university with Id - "+universityId;
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentService.findAll();
    }

    @GetMapping("/student/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        if(studentService.findById(studentId) == null){
           throw new StudentNotFoundException("Student Not Found : "+studentId);
        }
        return studentService.findById(studentId);
    }

    @GetMapping("/students/{universityId}")
    public List<Student> getStudentsOfUniversity(@PathVariable int universityId){
        return studentService.findByUniversityId(universityId);
    }

    @PostMapping("/students/{universityId}")
    public StudentDTO addStudent(@PathVariable int universityId, @RequestBody StudentDTO studentDTO){
        studentDTO.setUniversity(universityService.findById(universityId));
        studentDTO.setId(0);
        studentService.save(studentDTO);
        return studentDTO;
    }

    @PutMapping("/students/{universityId}")
    public StudentDTO updateStudent(@PathVariable int universityId,@RequestBody StudentDTO studentDTO){
        studentDTO.setUniversity(universityService.findById(universityId));
        studentService.save(studentDTO);
        return studentDTO;
    }
    @DeleteMapping("/student/{studentId}")
    public String deleteStudent(@PathVariable int studentId){
        if(studentService.findById(studentId) == null){
            throw new StudentNotFoundException("student does not exist - " + studentId);
        }
        studentService.deleteById(studentId);
        return "Deleted student with Id - "+studentId;
    }

}