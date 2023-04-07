package ru.job4j.services;

import ru.job4j.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    void add(Task task);
    void update(Task task);
    void updateDone(int id);
    void delete(int id);
    List<Task> findAll();
    Optional<Task> findById(int id);
    List<Task> findByDone(boolean done);
}
