package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Priority;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class PriorityRepository {
    private final CrudRepository repository;

    public Collection<Priority> findAll() {
        return repository.query("from Priority", Priority.class);
    }
}
