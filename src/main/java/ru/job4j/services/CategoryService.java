package ru.job4j.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Category;
import ru.job4j.repository.CategoryRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public Collection<Category> findAll() {
        return repository.findAll();
    }
}
