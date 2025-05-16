package com.amr_saleh.springboot.learn_jpa_and_hibernate.course;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.amr_saleh.springboot.learn_jpa_and_hibernate.course.jdbc.CourseJdbcRepository;
import com.amr_saleh.springboot.learn_jpa_and_hibernate.course.jpa.Course;
import com.amr_saleh.springboot.learn_jpa_and_hibernate.course.jpa.CourseJpaRepository;
import com.amr_saleh.springboot.learn_jpa_and_hibernate.course.springdatajpa.CourseSpringDataJpaRepository;

@Component
public class CourseCommandRunner implements CommandLineRunner {

    // @Autowired
    // private CourseJdbcRepository repository;

    // @Autowired
    // private CourseJpaRepository repository;

    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Course(1l, "Spring and SpringBoot Spring Data JPA","Amr Saleh"));
        repository.save(new Course(2l, "SpringBoot MicroServices Spring Data JPA","Amr Saleh"));
        repository.save(new Course(3l, "Software Architechture Patterns Spring Data JPA","Amr Saleh"));
        repository.deleteById(2l);

        Optional<Course> courseOptional= repository.findById(1l);
        if(courseOptional.isPresent()){
            Course course = courseOptional.get();
            course.setName("Spring and SpringBoot Spring Data JPA Updated");
            course.setAuthor("Amr-Saleh");
            repository.save(course);
        }else{
            System.out.println("Course not found");
        }

        System.out.println(repository.findAll());
        System.out.println(repository.count());
        System.out.println(repository.findByAuthor("Amr Saleh"));
    }
    
}
