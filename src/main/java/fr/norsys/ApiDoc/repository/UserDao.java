package fr.norsys.ApiDoc.repository;

import fr.norsys.ApiDoc.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserDao {
    public long save(User user);

    public List<User> findAll();
    public Optional<User> findById(Long id);
}
