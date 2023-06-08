package com.springboot.assignment.dto;

import com.springboot.assignment.entity.Student;
import com.springboot.assignment.entity.University;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
@Data
public class StudentDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private University university;

    @Autowired
    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
    }

    public static Student convertDtoToEntity(StudentDTO studentDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(studentDTO,Student.class);
    }

    public static StudentDTO convertEntityToDto(Student student){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(student,StudentDTO.class);
    }

    public StudentDTO(){

    }

    public StudentDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", university='" + university + '\'' +
                '}';
    }
}
