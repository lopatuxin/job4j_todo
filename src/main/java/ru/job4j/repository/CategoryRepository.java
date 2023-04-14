package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Category;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CategoryRepository {
    private final CrudRepository repository;

    public List<Category> findAll() {
        return repository.query("FROM Category", Category.class);
    }

    public List<Category> findById(List<Integer> list) {
        return repository.query("FROM Category WHERE id in :fId",
                Category.class, Map.of("fId", list)
        );
    }
}
