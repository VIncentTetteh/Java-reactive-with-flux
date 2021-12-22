package com.chrisbone.reactiveprogramming.service;

import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import com.chrisbone.reactiveprogramming.dao.StudentDao;
import com.chrisbone.reactiveprogramming.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao dao;

    //Synchronise and blocking example using list
    public List<Student> students(){
        long startTime = System.currentTimeMillis();
        List<Student> student = dao.allStudents();
        long endTime = System.currentTimeMillis();
        System.out.println("execution time is " + (endTime - startTime));
        return student;
    }

    //Asynchronise and non-blocking example using flux
    public Flux<Student> studentsStream(){
        long startTime = System.currentTimeMillis();
        Flux<Student> student = dao.allStudentsStream();
        long endTime = System.currentTimeMillis();
        System.out.println("execution time is " + (endTime - startTime));
        return student;
    }


}
