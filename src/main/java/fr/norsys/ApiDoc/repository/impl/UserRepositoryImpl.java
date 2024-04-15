package fr.norsys.ApiDoc.repository.impl;

import fr.norsys.ApiDoc.model.User;
import fr.norsys.ApiDoc.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
@Repository
@AllArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {
private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
private final Properties sqlProperties;
private static final String USER_ID = "id";
private static final String USER_GET_ALL="user.getAll";
private static final String USER_GET_ONE="user.getOne";
private static final String USER_CREATE="user.create";

    public long save(User user){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlProperties.getProperty(USER_CREATE), getUserParams(user), keyHolder, new String[]{USER_ID});
        return keyHolder.getKey().longValue();

    }
    public List<User> findAll() {
        return namedParameterJdbcTemplate.query(sqlProperties.getProperty(USER_GET_ALL), User::baseMapper);
    }
    public Optional<User> findById(Long id) {
        Optional<User> user = Optional.empty();
        try {
            user = Optional.of(namedParameterJdbcTemplate.queryForObject(sqlProperties.getProperty(USER_GET_ONE), new MapSqlParameterSource(USER_ID, id), User::baseMapper));
        } catch (DataAccessException dataAccessException) {
            log.info("User does not exist" + id);
        }
        return user;
    }
    private MapSqlParameterSource getUserParams(User user) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("username", user.getUsername());
        parameters.addValue("email", user.getEmail());
        parameters.addValue("password", user.getPassword());
        return parameters;
    }

}
