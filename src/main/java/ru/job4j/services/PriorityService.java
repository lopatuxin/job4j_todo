package ru.job4j.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Priority;
import ru.job4j.repository.PriorityRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class PriorityService {
    private final PriorityRepository repository;

    public Collection<Priority> findAll() {
        return repository.findAll();
    }
}
