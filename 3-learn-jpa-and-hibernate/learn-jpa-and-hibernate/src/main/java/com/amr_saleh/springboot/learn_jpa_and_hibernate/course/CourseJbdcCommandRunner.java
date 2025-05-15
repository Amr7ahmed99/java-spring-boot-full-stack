package com.amr_saleh.springboot.learn_jpa_and_hibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseJbdcCommandRunner implements CommandLineRunner {

    @Autowired
    private CourseJdbcRepository courseJdbcRepository;

    @Override
    public void run(String... args) throws Exception {
        courseJdbcRepository.insert(new Course(1l, "Spring and SpringBoot","Amr Saleh"));
        courseJdbcRepository.insert(new Course(2l, "SpringBoot MicroServices","Amr Saleh"));
        courseJdbcRepository.insert(new Course(3l, "Software Architechture Patterns","Amr Saleh"));
        courseJdbcRepository.deleteById(2l);

        System.out.println(courseJdbcRepository.findById(1l));
        System.out.println(courseJdbcRepository.findById(3l));

        courseJdbcRepository.update(new Course(1l, "Spring and SpringBoot Updated","Amr-Saleh"));
    }
    
}
