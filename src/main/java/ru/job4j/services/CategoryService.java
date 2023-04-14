package ru.job4j.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Category;
import ru.job4j.repository.CategoryRepository;

import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@Service
@AllArgsConstructor
@ThreadSafe
public class CategoryService {
    private final CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public List<Category> findById(List<Integer> list) {
        return repository.findById(list);
    }
}
