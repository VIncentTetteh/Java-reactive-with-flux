package com.chrisbone.reactiveprogramming.controller;

import com.chrisbone.reactiveprogramming.dto.Student;
import com.chrisbone.reactiveprogramming.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    //synchronise and blocking example using list
    @GetMapping("/students")
    public List<Student> studentList(){
        return studentService.students();
    }
    //asynchronise and non-blocking example using flux
    @GetMapping(value = "/studentstream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> studentStream(){
        return studentService.studentsStream();
    }
}
