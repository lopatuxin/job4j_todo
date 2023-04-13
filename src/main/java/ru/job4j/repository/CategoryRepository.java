package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Category;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class CategoryRepository {
    private final CrudRepository repository;

    public Collection<Category> findAll() {
        return repository.query("FROM Category", Category.class);
    }
}
