package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmUserRepository implements UserRepository {
    private final CrudRepository repository;

    @Override
    public User save(User user) {
        repository.run(session -> session.persist(user));
        return user;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return repository.optional("FROM User WHERE email = :fEmail AND password = :fPassword",
                User.class,
                Map.of("fEmail", email, "fPassword", password));
    }
}
