package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmTaskRepository implements TaskRepository {
    private final CrudRepository repository;

    @Override
    public Task add(Task task) {
        repository.run(session -> session.persist(task));
        return task;
    }

    @Override
    public boolean update(Task task) {
        return repository.booleanRun(session -> {
            session.merge(task);
            return true;
        });
    }

    public boolean updateDone(int id) {
        return repository.booleanRun("UPDATE Task SET done = :fDone WHERE id = :fId",
                Map.of("fDone", true, "fId", id));
    }

    @Override
    public boolean delete(int id) {
        return repository.booleanRun("DELETE Task WHERE id = :fId",
                Map.of("fId", id));
    }

    @Override
    public List<Task> findAll() {
        return repository.query("FROM Task", Task.class);
    }

    @Override
    public Optional<Task> findById(int id) {
        return repository.optional("FROM Task WHERE id = :fId", Task.class,
                Map.of("fId", id));
    }

    @Override
    public List<Task> findByDone(boolean done) {
        return repository.query("FROM Task WHERE done = :fDone", Task.class,
                Map.of("fDone", done));
    }
}
