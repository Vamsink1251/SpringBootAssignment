package com.springboot.assignment.exception;

import com.springboot.assignment.entity.University;

public class UniversityNotFoundException extends RuntimeException{

        public UniversityNotFoundException(String message){
            super(message);
        }

}
