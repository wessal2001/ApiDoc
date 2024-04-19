package fr.norsys.ApiDoc.repository.impl;

import fr.norsys.ApiDoc.model.Autorisation;
import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.model.User;
import fr.norsys.ApiDoc.repository.AutorisationDao;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


@Repository
@RequiredArgsConstructor
@Slf4j
public class AutorisationDaoImpl implements AutorisationDao {

    private  static final String INSERT_AUTORISATION="insert.autorisation";
    private static final String GET_DOCS_AUTORISATIONS="autorisation.getByUserAndDoc";
    private static final String AUTHORITY_GET_ALL = "autorisation.getAll";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Resource(name = "autorisationProperties")
    private final Properties properties;

    @Override
    public Optional<Autorisation> shareDocument(Autorisation autorisation) {
     jdbcTemplate.update(properties.getProperty(INSERT_AUTORISATION),getSqlParameterSource(autorisation));
     return  Optional.of(autorisation);
    }

    @Override
    public List<Autorisation> getDocAuthorities(int idDoc, int idUser) {
        List<Autorisation> autorisations=new ArrayList<>();
        try {
            autorisations = jdbcTemplate.query(properties.getProperty(GET_DOCS_AUTORISATIONS), new MapSqlParameterSource().addValue("iddocument",idDoc).addValue("iduser",idUser).addValue("droitacces","%"), Autorisation::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.info("error fetching authorities");
        }
        return autorisations;
    }

    @Override
    public List<Autorisation> getAllAuthorities() {
        return jdbcTemplate.query(properties.getProperty(AUTHORITY_GET_ALL), Autorisation::baseMapper);

    }


    private MapSqlParameterSource getSqlParameterSource(final Autorisation autorisation) {
        return new MapSqlParameterSource()
                .addValue("iddocument",autorisation.getIdDocument())
                .addValue("iduser",autorisation.getIdUser())
                .addValue("droitacces",autorisation.getDroitAcces());
    }
}
