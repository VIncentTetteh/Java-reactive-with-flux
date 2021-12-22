package com.chrisbone.reactiveprogramming.dao;

import com.chrisbone.reactiveprogramming.dto.Student;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class StudentDao {

    private static void sleepExecutor(int i){
        try {
             Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //synchronise and blocking example using list
    public List<Student> allStudents(){
        return IntStream.range(1,50)
                .peek(StudentDao::sleepExecutor)
                .peek(i -> System.out.println("processing count " + i))
                .mapToObj(obj -> new Student(obj, "student" + obj))
                .collect(Collectors.toList());
    }

    //asynchronise and non-blocking example using flux
    public Flux<Student> allStudentsStream(){
        return Flux.range(1, 50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing count in stream:" + i))
                .map(i -> new Student(i, "student" + i));
    }
}
