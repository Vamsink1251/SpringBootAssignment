package com.springboot.assignment.controller;

import com.springboot.assignment.dto.StudentDTO;
import com.springboot.assignment.dto.UniversityDTO;
import com.springboot.assignment.entity.Student;
import com.springboot.assignment.entity.University;
import com.springboot.assignment.service.StudentService;
import com.springboot.assignment.service.UniversityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UniversityControllerTest {
    @Mock
    private UniversityService universityService;
    @Mock
    private StudentService studentService;
    @Mock
    private Model model;
    @InjectMocks
    private UniversityController universityController;


    @Test
    public void testUniversityList(){
        // given
        List<University> universityList = new ArrayList<>();
        universityList.add(new University("Arizona State University","www.arizona.edu" ));
        universityList.add(new University("Princeton University", "www.princeton.edu"));
        when(universityService.findAll()).thenReturn(universityList);
        //when
        String view = universityController.universityList(model);
        assertEquals("university-list",view);
        verify(universityService,times(1)).findAll();

    }
    @Test
    public void testShowFormForAdd(){
        String view = universityController.showFormForAddUniversity(model);
        assertEquals("university-form",view);
        verify(model,times(1)).addAttribute(eq("university"),any(University.class));
    }
    @Test
    public void testShowFormForUpdate(){
        //given
        University university = new University();
        university.setId(1);
        when(universityService.findById(1)).thenReturn(university);
        //when
        String view = universityController.showFormForUpdateUniversity(1,model);
        //then
        assertEquals("university-form",view);
        verify(universityService,times(1)).findById(1);
        verify(model,times(1)).addAttribute(eq("university"),any(University.class));
    }
    @Test
    public void testSaveUniversity(){
        UniversityDTO universityDTO = new UniversityDTO();
        String view = universityController.saveUniversity(universityDTO);
        assertEquals("redirect:/university/list",view);
        verify(universityService,times(1)).save(universityDTO);
    }
    @Test
    public void testDelete(){
        University university = new University();
        university.setId(1);
        String view = universityController.deleteUniversity(1);
        assertEquals("redirect:/university/list",view);
        verify(universityService,times(1)).deleteById(1);
    }
    @Test
    public void testGetStudents(){
        //given
        List<Student> students = new ArrayList<>();
        students.add(new Student("vara", "prasad", "prasad@example.com"));
        students.add(new Student("sai", "prasad", "sai@example.com"));

        when(studentService.findByUniversityId(1)).thenReturn(students);
        //when
        String view= universityController.getStudents(1,model);
        //then
        assertEquals("student-list",view);
        verify(studentService,times(1)).findByUniversityId(1);
    }
    @Test
    public void testShowFormForAddStudent(){
        String view = universityController.showFormForAddStudent(1,model);
        assertEquals("student-form",view);
        verify(universityService,times(1)).findById(1);
        verify(model,times(1)).addAttribute(eq("student"),any(Student.class));
        verify(model,times(1)).addAttribute("universityId",1);
    }
    @Test
    public void testShowFormForUpdateStudent(){
        String view = universityController.showFormForUpdateStudent(1,1,model);
        assertEquals("student-form",view);
        verify(studentService,times(1)).findById(1);
        verify(model,times(1)).addAttribute("universityId",1);
    }
    @Test
    public void testSaveStudent(){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(1);
        studentDTO.setFirstName("sai");
        studentDTO.setLastName("prasad");
        studentDTO.setEmail("sai@gmail.com");
        studentDTO.setUniversity(new University("vishnuUniversity","www.vishnu.edu" ));
        studentDTO.getUniversity().setId(1);

        String view = universityController.saveStudent(studentDTO);
        assertEquals("redirect:/university/students/1",view);
        verify(studentService,times(1)).save(studentDTO);
    }
   @Test
    public void testDeleteStudent(){
        String view = universityController.deleteStudent(1,1);
        assertEquals("redirect:/university/students/1",view);
       verify(studentService,times(1)).deleteById(1);
    }
}
