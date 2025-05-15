package com.amr_saleh.springboot.learn_jpa_and_hibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.amr_saleh.springboot.learn_jpa_and_hibernate.course.jdbc.CourseJdbcRepository;
import com.amr_saleh.springboot.learn_jpa_and_hibernate.course.jpa.Course;
import com.amr_saleh.springboot.learn_jpa_and_hibernate.course.jpa.CourseJpaRepository;

@Component
public class CourseCommandRunner implements CommandLineRunner {

    // @Autowired
    // private CourseJdbcRepository repository;

    @Autowired
    private CourseJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1l, "Spring and SpringBoot JPA","Amr Saleh"));
        repository.insert(new Course(2l, "SpringBoot MicroServices JPA","Amr Saleh"));
        repository.insert(new Course(3l, "Software Architechture Patterns JPA","Amr Saleh"));
        repository.deleteById(2l);

        System.out.println(repository.findById(1l));
        System.out.println(repository.findById(3l));

        repository.update(new Course(1l, "Spring and SpringBoot JPA Updated","Amr-Saleh"));
    }
    
}
