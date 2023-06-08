package com.springboot.assignment.service;

import com.springboot.assignment.dao.UniversityRepository;
import com.springboot.assignment.dto.UniversityDTO;
import com.springboot.assignment.entity.University;
import com.springboot.assignment.exception.ListEmptyException;
import com.springboot.assignment.exception.UniversityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService{

    @Autowired
    private UniversityRepository universityRepository;
    @Override
    @Transactional
    public List<University> findAll() {
        List<University> result = universityRepository.findAll();
        if(result.isEmpty()){
            throw new ListEmptyException("University List is Empty");
        }
        else{
            return result;
        }
    }

    @Override
    public University findById(int theId) {
        Optional<University> result = universityRepository.findById(theId);

        University theUniversity;

        if (result.isPresent()) {
            theUniversity = result.get();
        }
        else {
            // we didn't find the University
            throw new UniversityNotFoundException("Did not find University with id - " + theId);
        }

        return theUniversity;
    }

    @Override
    public void save(UniversityDTO universityDTO) {
        universityRepository.save(UniversityDTO.convertDtoToEntity(universityDTO));
    }

    @Override
    public void deleteById(int theId) {
        Optional<University> result = universityRepository.findById(theId);

        if (result.isPresent()) {
            universityRepository.deleteById(theId);
        }
        else {
            // we didn't find the University
            throw new UniversityNotFoundException("Did not find University with id - " + theId);
        }
    }
}
