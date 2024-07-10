package com.example.demo.repository;

import com.example.demo.entity.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "todo", path = "todo")
public interface TodoRepository  extends PagingAndSortingRepository<Todo, Long>, CrudRepository<Todo,Long> {
    Optional<List<Todo>> findByName(@Param("name") String name);

    List<Todo> findAll();

    Optional<Todo> findById(long id);

}
