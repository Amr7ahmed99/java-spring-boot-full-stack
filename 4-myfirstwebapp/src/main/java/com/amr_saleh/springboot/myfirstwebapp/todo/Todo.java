package com.amr_saleh.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Component;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

/*  This annotation indicates that this class is a JPA entity and will be mapped to a database table, this step is done automatically by Spring Boot when using JPA
    And it is one of steps that is done before autoConfiguration, so you don't need to do anything else to enable JPA.
*/
@Entity
public class Todo {
    @Id
    @GeneratedValue // This annotation indicates that the id field is the primary key and will be generated automatically by the database
    private int id;
    @Size(min=10, message="Enter at least 10 characters")
    private String description;
    private boolean done;
    private String targetDate;
    @Column(name="username") // This is to specify the column name in the database table, it is optional if the field name is the same as the column name
    private String user;

    public Todo() {
        super();
    }

    public Todo(int id, String description, boolean done, String targetDate, String user) {
        super();
        this.id = id;
        this.description = description;
        this.done = done;
        this.targetDate = targetDate;
        this.user = user;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public String getTargetDate() {
        return targetDate;
    }
    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "Todo [id=" + id + ", description=" + description + ", done=" + done + ", targetDate=" + targetDate
                + ", user=" + user + "]";
    }
}
