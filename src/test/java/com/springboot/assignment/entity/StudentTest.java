package com.springboot.assignment.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


 class StudentTest {
    private Student student;
    private University university;


    @Test
     void testNoArgsConstructor(){
        student=new Student();
        assertNotNull(student);
    }

    @Test
     void testArgsConstructor(){
        student = new Student("sai","prasad","sai@gmail.com");
        assertEquals("sai",student.getFirstName());
        assertEquals("prasad",student.getLastName());
        assertEquals("sai@gmail.com",student.getEmail());

    }
    @Test
     void testId(){
        student = new Student("sai","prasad","sai@gmail.com");
        student.setId(1);
        assertEquals(1,student.getId());
    }
    @Test
     void testFirstName(){
        student = new Student("sai","prasad","sai@gmail.com");
        assertEquals("sai",student.getFirstName());
    }
    @Test
     void testLastName(){
        student = new Student("sai","prasad","sai@gmail.com");
        assertEquals("prasad",student.getLastName());
    }
    @Test
     void testEmail(){
        student = new Student("sai","prasad","sai@gmail.com");
        assertEquals("sai@gmail.com",student.getEmail());
    }

    @Test
     void testSetFirstName(){
        student = new Student();
        student.setFirstName("sai");
        assertEquals("sai",student.getFirstName());

    }
    @Test
     void testSetLastName(){
        student = new Student();
        student.setLastName("prasad");
        assertEquals("prasad",student.getLastName());
    }
    @Test
     void testSetEmail(){
        student = new Student();
        student.setEmail("sai@gmail.com");
        assertEquals("sai@gmail.com",student.getEmail());
    }



    @Test
     void testUniversity(){
        student=new Student("sai","prasad","sai@gmail.com");
        university=new University("University","www.university.edu");
        student.setUniversity(university);
        assertEquals(university,student.getUniversity());
    }
    @Test
     void testToString(){
        student = new Student("sai","prasad","sai@gmail.com");
        String result = student.toString();
        assertEquals("Student{id=0, firstName=sai, lastName=prasad, email=sai@gmail.com}",result);
    }
}
