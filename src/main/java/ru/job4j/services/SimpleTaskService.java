package ru.job4j.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Task;
import ru.job4j.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {
    private final TaskRepository repository;

    @Override
    public void add(Task task) {
        repository.add(task);
    }

    @Override
    public void update(Task task) {
        repository.update(task);
    }

    @Override
    public void updateDone(int id) {
        repository.updateDone(id);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Task> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Task> findByDone(boolean done) {
        return repository.findByDone(done);
    }
}
