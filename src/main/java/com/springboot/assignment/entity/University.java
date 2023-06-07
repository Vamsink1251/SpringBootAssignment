package com.springboot.assignment.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "university")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "uni_name")
    private String name;

    @Column(name = "website")
    private String website;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Student> studentList;

    public University(){

    }

    public University(String name, String website){
        this.name = name;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    // add convenient method
    public void add(Student student){
        if(studentList==null){
            studentList=new ArrayList<>();
        }
        studentList.add(student);
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", Name=" + name +
                ", website=" + website +
                "}";
    }
}
