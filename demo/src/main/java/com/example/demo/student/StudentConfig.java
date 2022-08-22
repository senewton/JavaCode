package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args-> {
            Student sean  = new Student(
                    "Sean",
                    LocalDate.of(1966, Month.OCTOBER,14),
                    "sean.newton@postnl.nl" );
            Student cheng  = new Student(
                    "Cheng",
                    LocalDate.of(1965, Month.JULY,11),
                    "cheng.newton@gmail.com" );
            Student yvonne  = new Student(
                    "Yvonne",
                    LocalDate.of(1997, Month.JULY,14),
                    "yvonnie_n@gmail.com" );

            repository.saveAll(List.of(sean, cheng, yvonne));
        };
    }
}
