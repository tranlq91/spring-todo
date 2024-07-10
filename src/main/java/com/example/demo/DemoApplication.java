package com.example.demo;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(TodoRepository repository) {
		return (args) -> {
			// save a few customers
			log.info("Creating todo list");
			log.info("-------------------------------");
			repository.save(new Todo("Java", true));
			repository.save(new Todo("JavaScript", true));
			repository.save(new Todo("Golang", false));
			repository.save(new Todo("Python", true));
			repository.save(new Todo("C#", false));

			// fetch all todos
			log.info("Fetching todos");
			log.info("-------------------------------");
			repository.findAll().forEach(todo -> {
				log.info(todo.toString());
			});

			// Fetch a todo by Id
			Optional<Todo> todo = repository.findById(1L);
			if (todo.isPresent()) {
				log.info("Todo found with findById(1L):");
				log.info("--------------------------------");
				log.info(todo.get().toString());
				log.info("");
			}

			// fetch todos by name
			log.info("Todos found with findByName('Java'):");
			log.info("--------------------------------------------");
			repository.findByName("Java").orElse(Collections.emptyList()).forEach(name -> {
				log.info(name.toString());
			});
			log.info("Finishing setup");
		};
	}

}
