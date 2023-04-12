package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmUserRepository implements UserRepository {
    private final CrudRepository repository;
    private static final Logger LOG = LoggerFactory.getLogger(HbmUserRepository.class);

    @Override
    public Optional<User> save(User user) {
        Optional result = Optional.empty();
        try {
            repository.run(session -> session.persist(user));
            result = Optional.of(user);
        } catch (Exception e) {
            LOG.error("Пользователь с таким email уже существует", e);
        }
        return result;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return repository.optional("FROM User WHERE email = :fEmail AND password = :fPassword",
                User.class,
                Map.of("fEmail", email, "fPassword", password));
    }
}
