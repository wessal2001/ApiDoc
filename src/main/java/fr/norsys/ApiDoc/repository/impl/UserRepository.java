package fr.norsys.ApiDoc.repository.impl;

import fr.norsys.ApiDoc.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository {
    public long save(User user);

    public List<User> findAll();
    public Optional<User> findById(Long id);
}
