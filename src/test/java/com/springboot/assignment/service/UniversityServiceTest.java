package com.springboot.assignment.service;


import com.springboot.assignment.dao.UniversityRepository;
import com.springboot.assignment.dto.UniversityDTO;
import com.springboot.assignment.entity.University;
import com.springboot.assignment.exception.ListEmptyException;
import com.springboot.assignment.exception.UniversityNotFoundException;
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
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class UniversityServiceTest {
    @Mock
    private UniversityRepository universityRepository;
    @InjectMocks
    private UniversityServiceImpl universityService;

    @BeforeEach
    public void setUp(){
        // MockitoAnnotations. openMocks(this) call tells Mockito to scan this test class instance for any fields annotated with the @Mock annotation and initialize those fields as mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll(){
        //given
        List<University> universityList = new ArrayList<>();
        universityList.add(new University("Vishnu University","www.vishnu.edu"));
        universityList.add(new University("Jntuk University", "www.jntuk.edu"));
        //when
        when(universityRepository.findAll()).thenReturn(universityList);
        List<University> universities = universityService.findAll();

        // then
        assertEquals(universityList,universities);
        assertEquals("Vishnu University",universities.get(0).getName());
        assertEquals(2,universities.size());

    }

    @Test
    public void testFindAllListEmpty(){
        Throwable exception = assertThrows(ListEmptyException.class,()->universityService.findAll());
        assertEquals("University List is Empty",exception.getMessage());
    }


    @Test
    public void testFindById(){
        //given
        University university = new University( "Vishnu University","www.vishnu.edu");
        //when
        when(universityRepository.findById(0)).thenReturn(Optional.of(university));
        University university1=universityService.findById(0);
        //then
        assertEquals(university,university1);
    }
    @Test(expected = RuntimeException.class)
    public void testFindByIdNotFound(){
        universityService.findById(100);
    }

    @Test
    public void testSave(){
        //given
        University university = new University("Vishnu University","www.vishnu.edu");
        UniversityDTO universityDTO = UniversityDTO.convertEntityToDto(university);
        //when
        universityService.save(universityDTO);
        //then
        when(universityRepository.findById(1)).thenReturn(Optional.of(university));
        University actualUniversity = universityService.findById(1);
        assertEquals(university,actualUniversity);
    }
    @Test
    public void testDeleteIdFound(){
        University university = new University("Vishnu University","www.vishnu.edu");
        university.setId(1);
        when(universityRepository.findById(1)).thenReturn(Optional.of(university));
        //when
        universityService.deleteById(1);
        //then
        verify(universityRepository,times(1)).deleteById(1);
    }
    @Test
    public void testDeleteByIdNotFound(){
        Throwable exception = assertThrows(UniversityNotFoundException.class,()-> universityService.deleteById(1));
        assertEquals("Did not find University with id - "+1,exception.getMessage());

    }
}

