package ru.job4j.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Task;
import ru.job4j.model.User;
import ru.job4j.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {
    private final TaskRepository repository;

    @Override
    public Task add(Task task) {
        return repository.add(task);
    }

    @Override
    public boolean update(Task task) {
        return repository.update(task);
    }

    @Override
    public boolean updateDone(int id) {
        return repository.updateDone(id);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
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
