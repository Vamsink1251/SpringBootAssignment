package com.springboot.assignment.dto;


import com.springboot.assignment.entity.Student;
import com.springboot.assignment.entity.University;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Data
public class UniversityDTO {
    private int id;
    private String name;
    private String website;

    private List<Student> studentList;

    @Autowired
    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
    }

    public static University convertDtoToEntity(UniversityDTO universityDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(universityDTO, University.class);
    }

    public static UniversityDTO convertEntityToDto(University university){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(university,UniversityDTO.class);
    }

    public UniversityDTO(){

    }

    public UniversityDTO(String name, String website) {
        this.name = name;
        this.website = website;
    }
    @Override
    public String toString() {
        return "UniversityDTO{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
