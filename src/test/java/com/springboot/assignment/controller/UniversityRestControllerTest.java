package com.springboot.assignment.controller;

import com.springboot.assignment.dto.StudentDTO;
import com.springboot.assignment.dto.UniversityDTO;
import com.springboot.assignment.entity.Student;
import com.springboot.assignment.entity.University;
import com.springboot.assignment.exception.StudentNotFoundException;
import com.springboot.assignment.exception.UniversityNotFoundException;
import com.springboot.assignment.service.StudentService;
import com.springboot.assignment.service.UniversityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UniversityRestControllerTest {
    @Mock
    private UniversityService universityService;
    @Mock
    private StudentService studentService;
    @InjectMocks
    private UniversityRestController universityRestController;

    @Test
    public void testGetUniversities(){
        List<University> universityList = new ArrayList<>();
        universityList.add(new University("vishnuUniversity","www.vishnu.edu"));
        universityList.add(new University("jntuk University","www.jntuk.ao"));

        //when
        when(universityService.findAll()).thenReturn(universityList);
        List<University> universities = universityRestController.getUniversities();
        assertEquals(universityList,universities);
    }
    @Test
    public void testGetUniversityById(){
        University university = new University();
        //when
        when(universityService.findById(1)).thenReturn(university);
        University university1 = universityRestController.getUniversityById(1);
        assertEquals(university,university1);
    }
    @Test(expected = RuntimeException.class)
    public void testGetUniversityByIdNotFound(){
        University university = universityRestController.getUniversityById(1);
    }

    @Test
    public void testGetUniversityByIdNotFound2(){
        Throwable exception = assertThrows(UniversityNotFoundException.class,()-> {
            University university = universityRestController.getUniversityById(12);
        });
        assertEquals("The University not found : "+"12",exception.getMessage());

    }
    @Test
    public void testAddUniversity(){
        UniversityDTO universityDTO = new UniversityDTO();
        UniversityDTO university1 = universityRestController.addUniversity(universityDTO);
        assertEquals(universityDTO,university1);
        verify(universityService,times(1)).save(universityDTO);
    }
    @Test
    public void testUpdateUniversity(){
        UniversityDTO universityDTO = new UniversityDTO();
        UniversityDTO university1 = universityRestController.updateUniversity(universityDTO);
        assertEquals(universityDTO,university1);
        verify(universityService,times(1)).save(universityDTO);
    }
    @Test
    public void testDeleteUniversity(){
        University university = new University();
        university.setId(1);
        String expected = "Deleted university with Id - 1";

        when(universityService.findById(1)).thenReturn(university);
        String actual = universityRestController.deleteUniversity(1);

        assertEquals(expected,actual);
        verify(universityService,times(1)).deleteById(1);
    }
    @Test(expected = RuntimeException.class)
    public void testDeleteUniversityIdNotFound(){
        universityRestController.deleteUniversity(1);
    }
    @Test
    public void testGetStudents(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("vara","prasad","prasad@gmail.com"));
        studentList.add(new Student("sai","prasad","sai@gmail.com"));

        when(studentService.findAll()).thenReturn(studentList);
        List<Student> students = universityRestController.getStudents();

        assertEquals(studentList,students);
        verify(studentService,times(1)).findAll();

    }
    @Test
    public void testGetStudentById(){
        Student student = new Student();
        student.setId(1);

        when(studentService.findById(1)).thenReturn(student);

        Student student1 = universityRestController.getStudentById(1);

        assertEquals(student,student1);
        verify(studentService,times(2)).findById(1);
    }
    @Test(expected = RuntimeException.class)
    public void testGetStudentByIdNotFound(){
        universityRestController.getStudentById(1);
    }
    @Test
    public void testGetStudentsByUniversityID(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("vara","prasad","prasad@gmail.com"));
        studentList.add(new Student("sai","prasad","sai@gmail.com"));

        when(studentService.findByUniversityId(1)).thenReturn(studentList);
        List<Student> students = universityRestController.getStudentsOfUniversity(1);

        assertEquals(studentList,students);
        verify(studentService,times(1)).findByUniversityId(1);
    }
    @Test
    public void testAddStudent(){
        University university = new University();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(0);
        studentDTO.setUniversity(university);
        when(universityService.findById(1)).thenReturn(university);

        StudentDTO student1 = universityRestController.addStudent(1,studentDTO);

        assertEquals(studentDTO,student1);
        verify(universityService,times(1)).findById(1);
        verify(studentService,times(1)).save(studentDTO);
    }
    @Test
    public void testUpdateStudent(){
        University university = new University();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(1);
        studentDTO.setUniversity(university);

        when(universityService.findById(1)).thenReturn(university);
        StudentDTO student1 = universityRestController.updateStudent(1,studentDTO);

        assertEquals(studentDTO,student1);
        verify(universityService,times(1)).findById(1);
        verify(studentService,times(1)).save(studentDTO);
    }
    @Test
    public void testDeleteStudent(){
        Student student = new Student();
        when(studentService.findById(1)).thenReturn(student);
        String actual = universityRestController.deleteStudent(1);

        assertEquals("Deleted student with Id - 1",actual);
        verify(studentService,times(1)).findById(1);
        verify(studentService,times(1)).deleteById(1);
    }
    @Test
    public void testDeleteStudentByIdNotFound(){
        Throwable exception = assertThrows(StudentNotFoundException.class,()->universityRestController.deleteStudent(1));
        assertEquals("student does not exist - "+1,exception.getMessage());
    }
    @Test(expected = RuntimeException.class)
    public void testDeleteByIdNotFound(){
        universityRestController.deleteUniversity(100);
    }
}

