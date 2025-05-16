package com.amr_saleh.springboot.learn_jpa_and_hibernate.course.springdatajpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.amr_saleh.springboot.learn_jpa_and_hibernate.course.jpa.Course;

public interface SpringDataJpaRepository extends JpaRepository<Course, Long> {
    // Spring Data JPA will automatically implement the methods for you
    // Custom method to find courses by author
    List<Course> findByAuthor(String author);
}
