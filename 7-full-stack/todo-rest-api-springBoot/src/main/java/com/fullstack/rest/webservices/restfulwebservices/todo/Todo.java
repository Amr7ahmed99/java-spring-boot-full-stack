package com.fullstack.rest.webservices.restfulwebservices.todo;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters, setters, toString, etc.
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {
	@Id
	@GeneratedValue
	Integer id;
	@NotNull
	String username;
	@NotNull
	String description;
	@NotNull
	LocalDate targetDate;
	boolean done;
}