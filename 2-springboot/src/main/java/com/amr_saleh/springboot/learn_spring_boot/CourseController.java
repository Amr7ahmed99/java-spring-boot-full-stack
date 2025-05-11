package com.amr_saleh.springboot.learn_spring_boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @RequestMapping("/courses")
    private List<Course> listCourses(){
        return Arrays.asList(
                new Course(1, "SpringBoot", "Amr-Saleh"),
                new Course(2, "GoLang", "Amr-Saleh")
        );
    }
}
