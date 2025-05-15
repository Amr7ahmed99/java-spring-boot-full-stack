package com.amr_saleh.springboot.learn_jpa_and_hibernate.course.jpa;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseJpaRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void insert(Course course) {
        entityManager.merge(course);
    }

    public Course findById(long id){
        return entityManager.find(Course.class, id);
    }

    public void update(Course course) {
        Course courseToBeUpdated = entityManager.find(Course.class, course.getId());
        courseToBeUpdated.setName(course.getName());
        courseToBeUpdated.setAuthor(course.getAuthor());
        entityManager.merge(courseToBeUpdated);
    }
    
    public void deleteById(long id){
        Course course = this.findById(id);
        entityManager.remove(course);
    }
}
