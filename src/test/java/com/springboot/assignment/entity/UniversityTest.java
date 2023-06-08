package com.springboot.assignment.entity;



import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


 class UniversityTest {
    private University university;
    private List<Student> studentList;

    @Test
     void testEmptyConstructor(){
        university = new University();
        assertNotNull(university);
    }

    @Test
     void testArgsConstructor(){
        university = new University("vishnuUniversity", "www.vishnu.edu");
        assertEquals("vishnuUniversity",university.getName());
        assertEquals("www.vishnu.edu",university.getWebsite());
    }
    @Test
     void testId() {
       university = new University("vishnuUniversity", "www.vishnu.edu");
        university.setId(1);
        assertEquals(1, university.getId());
    }
    @Test
     void testGetName() {
       university = new University("vishnuUniversity", "www.vishnu.edu");
        assertEquals("vishnuUniversity", university.getName());
    }

    @Test
     void testGetWebsite() {
       university = new University("vishnuUniversity", "www.vishnu.edu");
        assertEquals("www.vishnu.edu", university.getWebsite());
    }

    @Test
     void testSetName() {
        university=new University();
        university.setName("vishnuUniversity");
        assertEquals("vishnuUniversity", university.getName());
    }

    @Test
     void testSetWebsite() {
        university=new University();
        university.setWebsite("www.vishnu.edu");
        assertEquals("www.vishnu.edu", university.getWebsite());
    }



    @Test
     void testGetStudents(){
        university=new University("vishnuUniversity", "www.vishnu.edu");
        studentList = new ArrayList<>();
        studentList.add(new Student("vara", "prasad", "prasad@example.com"));
        studentList.add(new Student("sai", "prasad", "sai@example.com"));
        university.setStudentList(studentList);

        assertEquals(studentList,university.getStudentList());
    }

    @Test
     void testAdd(){
        university=new University("vishnuUniversity", "www.vishnu.edu");
        Student student = new Student("vara","prasad","prasad@example.com");
        university.add(student);
        assertEquals(student,university.getStudentList().get(0));

    }
    @Test
     void testToString() {
        university=new University("vishnuUniversity", "www.vishnu.edu");
        String result = university.toString();
        assertEquals("University{id=0, Name=vishnuUniversity, website=www.vishnu.edu}",result);
    }
}
