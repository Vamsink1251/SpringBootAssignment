package com.springboot.assignment.service;

import com.springboot.assignment.dto.UniversityDTO;
import com.springboot.assignment.entity.University;

import java.util.List;



public interface UniversityService {

    List<University> findAll();

    University findById(int theId);


    void save(UniversityDTO universityDTO);

    void deleteById(int theId);

}
