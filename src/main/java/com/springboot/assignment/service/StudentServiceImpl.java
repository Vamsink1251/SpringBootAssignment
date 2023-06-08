package com.springboot.assignment.service;

import com.springboot.assignment.dao.StudentRepository;
import com.springboot.assignment.dao.UniversityRepository;
import com.springboot.assignment.dto.StudentDTO;
import com.springboot.assignment.entity.Student;
import com.springboot.assignment.entity.University;
import com.springboot.assignment.exception.ListEmptyException;
import com.springboot.assignment.exception.StudentNotFoundException;
import com.springboot.assignment.exception.UniversityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UniversityRepository universityRepository;


    @Override
    @Transactional
    public List<Student> findByUniversityId(int id) {
        Optional<University> result = universityRepository.findById(id);

        if (result.isPresent()) {
            return studentRepository.findByUniversityId(id);
        }
        else {
            // we didn't find the University
            throw new UniversityNotFoundException("Did not find University with id - " + id);
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> result = studentRepository.findAll();
        if(result.isEmpty()){
            throw new ListEmptyException("Student List is Empty");
        }
        else{
            return result;
        }
    }

    @Override
    public Student findById(int theId) {
        Optional<Student> result = studentRepository.findById(theId);

        Student student;

        if(result.isPresent()){
            student=result.get();
        }
        else {
            throw new StudentNotFoundException(" Did not find the Student with id - "+theId);
        }
        return student;
    }

    @Override
    @Transactional
    public void save(StudentDTO studentDTO) {
        studentRepository.save(StudentDTO.convertDtoToEntity(studentDTO));
    }

    @Override
    public void deleteById(int theId) {
        Optional<Student> result = studentRepository.findById(theId);
        if(result.isPresent()){
            studentRepository.deleteById(theId);
        }
        else {
            throw new StudentNotFoundException(" Did not find the Student with id - "+theId);
        }
    }
}
