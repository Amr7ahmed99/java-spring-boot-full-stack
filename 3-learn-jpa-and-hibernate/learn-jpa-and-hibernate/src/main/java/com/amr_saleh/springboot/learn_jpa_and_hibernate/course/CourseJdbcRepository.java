package com.amr_saleh.springboot.learn_jpa_and_hibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

    @Autowired
    private JdbcTemplate springJdbcTemplate;
    
    private final String INSERT_QUERY= """
            INSERT INTO course(id, name, author) 
            VALUES(?, ?, ?);
        """;
    private final String DELETE_QUERY= """
            DELETE FROM course 
            WHERE id = ?
        """;
    
    private final String SELECT_QUERY= """
            SELECT * FROM course 
            WHERE id = ?
        """;
    
    private final String UPDATE_QUERY= """
            UPDATE course 
            SET name = ?, author = ?
            WHERE id = ?
        """;

    public void insert(Course course) {
        springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteById(long id) {
        springJdbcTemplate.update(DELETE_QUERY, id);
    }

    public Course findById(long id){
        // the result-set is mapped to Course bean using RowMapper
        return springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
    }

    public void update(Course course) {
        springJdbcTemplate.update(UPDATE_QUERY, course.getName(), course.getAuthor(), course.getId());
    }
}
