package fr.norsys.ApiDoc.repository.impl;

import fr.norsys.ApiDoc.model.Autorisation;
import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.model.User;
import fr.norsys.ApiDoc.repository.AutorisationDao;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Properties;

@Repository
@RequiredArgsConstructor
public class AutorisationDaoImpl implements AutorisationDao {

    private  static final String INSERT_AUTORISATION="insert.autorisation";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Resource(name = "autorisationProperties")
    private final Properties properties;

    @Override
    public Optional<Autorisation> shareDocument(Autorisation autorisation) {
     jdbcTemplate.update(properties.getProperty(INSERT_AUTORISATION),getSqlParameterSource(autorisation));
     return  Optional.of(autorisation);
    }

    private MapSqlParameterSource getSqlParameterSource(final Autorisation autorisation) {
        return new MapSqlParameterSource()
                .addValue("iddocument",autorisation.getIdDocument())
                .addValue("iduser",autorisation.getIdUser())
                .addValue("droitacces",autorisation.getDroitAcces());
    }
}
