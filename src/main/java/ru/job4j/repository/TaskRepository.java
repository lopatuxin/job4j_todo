package ru.job4j.repository;

import ru.job4j.model.Task;
import ru.job4j.model.User;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task add(Task task);
    boolean update(Task task);
    boolean updateDone(int id);
    boolean delete(int id);
    List<Task> findAll();
    Optional<Task> findById(int id);
    List<Task> findByDone(boolean done);
}
