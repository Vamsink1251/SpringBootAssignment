package com.springboot.assignment.service;

import com.springboot.assignment.dao.StudentRepository;
import com.springboot.assignment.dao.UniversityRepository;
import com.springboot.assignment.dto.StudentDTO;
import com.springboot.assignment.entity.Student;
import com.springboot.assignment.entity.University;
import com.springboot.assignment.exception.ListEmptyException;
import com.springboot.assignment.exception.StudentNotFoundException;
import com.springboot.assignment.exception.UniversityNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private UniversityRepository universityRepository;
    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByUniversityId(){
        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student( "vara", "prasad", "prasad@example.com"));
        students.add(new Student( "sai", "prasad", "sai@example.com"));
        //when
        University university = new University("vishnuUniversity","www.vishnu.edu");
        university.setId(1);
        when(universityRepository.findById(1)).thenReturn(Optional.of(university));
        when(studentRepository.findByUniversityId(1)).thenReturn(students);

        List<Student> studentList = studentService.findByUniversityId(1);

        //then
        assertEquals(students,studentList);
    }

    @Test
    public void testFindByUniversityIdNotFound(){

        Throwable exception = assertThrows(UniversityNotFoundException.class,()-> studentService.findByUniversityId(1));
        assertEquals("Did not find University with id - "+1,exception.getMessage());
    }

    @Test
    public void testFindAll(){
        //given
        List<Student> students = new ArrayList<>();
        students.add(new Student( "vara", "prasad", "prasad@example.com"));
        students.add(new Student( "sai", "prasad", "sai@example.com"));

        //when
        when(studentRepository.findAll()).thenReturn(students);

        // Call the method to be tested
        List<Student> result = studentService.findAll();

        // then
        assertEquals(2, result.size());
        assertEquals("vara", result.get(0).getFirstName());
        assertEquals("sai", result.get(1).getFirstName());
    }

    @Test
    public void testFindAllListEmpty(){
        Throwable exception = assertThrows(ListEmptyException.class,()->studentService.findAll());
        assertEquals("Student List is Empty",exception.getMessage());
    }

    @Test
    public void testFindById(){
        //given
        Student student = new Student("sai", "prasad", "sai@example.com");
        student.setId(1);
        //when
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        Student student1 = studentService.findById(1);
        //then
        assertEquals(student,student1);
    }
    @Test(expected = RuntimeException.class)
    public void testFindByIdNotFound(){
        studentService.findById(1);
    }

    @Test
    public void testFindByIdNotFound2(){

        Throwable exception = assertThrows(StudentNotFoundException.class,()->{studentService.findById(1);});
        assertEquals(" Did not find the Student with id - "+1,exception.getMessage());
    }
    @Test
    public void testSave(){
        //given
        Student student = new Student("sai", "prasad", "sai@example.com");
        StudentDTO studentDTO = StudentDTO.convertEntityToDto(student);
        //when
        studentService.save(studentDTO);
        //then
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        Student actualStudent = studentService.findById(1);
        Assert.assertEquals(student,actualStudent);
    }
    @Test
    public void testDeleteIdFound(){
        //given
        Student student = new Student("sai", "prasad", "sai@example.com");
        student.setId(1);
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        // when
        studentService.deleteById(1);
        //then
        verify(studentRepository,times(1)).deleteById(1);
    }

    @Test
    public void testDeleteByIdNotFound(){
        Throwable exception = assertThrows(StudentNotFoundException.class,()->studentService.deleteById(1));
        assertEquals(" Did not find the Student with id - "+1,exception.getMessage());
    }
}
