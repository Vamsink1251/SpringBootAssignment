package com.springboot.assignment.controller;

import com.springboot.assignment.dto.StudentDTO;
import com.springboot.assignment.dto.UniversityDTO;
import com.springboot.assignment.entity.Student;
import com.springboot.assignment.entity.University;
import com.springboot.assignment.service.StudentService;
import com.springboot.assignment.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;
    @Autowired
    private StudentService studentService;

    private static final String UNIVERSITYID = "universityId";

    // adding the mapping for getting university list "/list"
    @GetMapping("/list")
    public String universityList(Model model) {
        // get the list of universities from the DB
        List<University> universityList = universityService.findAll();

        model.addAttribute("universities", universityList);

        return "university-list";
    }
    @GetMapping("/showFormForAddUniversity")
    public String showFormForAddUniversity(Model model){
        University university = new University();
        model.addAttribute("university",university);

        return "university-form";
    }

    @PostMapping("/saveUniversity")
    public String saveUniversity(@ModelAttribute("university") UniversityDTO universityDTO ){
        universityService.save(universityDTO);
        return "redirect:/university/list";
    }

    @GetMapping("/showFormForUpdateUniversity")
    public String showFormForUpdateUniversity(@RequestParam("universityId") int id, Model model){
        University university = universityService.findById(id);
        model.addAttribute("university",university);

        return "university-form";
    }

    @PostMapping("/deleteUniversity")
    public String deleteUniversity(@RequestParam("universityId") int id){
        universityService.deleteById(id);
        return "redirect:/university/list";
    }

    // Controllers for Students


    @GetMapping("/students/{universityId}")
    public String getStudents(@PathVariable int universityId,Model model){
        List<Student> studentList = studentService.findByUniversityId(universityId);
        model.addAttribute("students",studentList);
        model.addAttribute(UNIVERSITYID,universityId);
        return "student-list";
    }

    @PostMapping("/showFormForAddStudent")
    public String showFormForAddStudent(@RequestParam("universityId") int universityId, Model model){
        Student student = new Student();
        student.setUniversity(universityService.findById(universityId));
        model.addAttribute("student",student);
        model.addAttribute(UNIVERSITYID,universityId);

        return "student-form";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") StudentDTO studentDTO){
        studentService.save(studentDTO);
        int id = studentDTO.getUniversity().getId();
        return "redirect:/university/students/"+id;
    }

    @PostMapping("/showFormForUpdateStudent")
    public String showFormForUpdateStudent(@RequestParam("studentId") int id,@RequestParam("universityId") int universityId, Model model){
        Student student = studentService.findById(id);
        model.addAttribute("student",student);
        model.addAttribute(UNIVERSITYID,universityId);
        return "student-form";
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId") int id,@RequestParam("universityId") int universityId){
        studentService.deleteById(id);
        return "redirect:/university/students/"+universityId;
    }
}
