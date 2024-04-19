package fr.norsys.ApiDoc.repository.impl;

import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.model.Metadata;
import fr.norsys.ApiDoc.repository.MetadataDao;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Properties;

@Repository
@RequiredArgsConstructor
public class MetadataDaoImpl implements MetadataDao {
    private static final String SAVE_META ="metadata.add" ;
    private static final String DELETE_META ="metadata.delete" ;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Resource(name = "metadataProperties")
    private final Properties properties;
    @Override
    public Metadata saveMetadata(Metadata metadata) {
        jdbcTemplate.update(properties.getProperty(SAVE_META), getSqlParameterSource(metadata));
        return metadata;
    }

    @Override
    public int deleteMeatdate(int id) {
        return	jdbcTemplate.update(properties.getProperty(DELETE_META),new MapSqlParameterSource().addValue("iddocument", id));

    }

    private MapSqlParameterSource getSqlParameterSource(final Metadata metadata) {
        return new MapSqlParameterSource()
                .addValue("keyname",metadata.getKeyName())
                .addValue("value",metadata.getValue())
                .addValue("iddoc",metadata.getDocumentId());
    }

}
