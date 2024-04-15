package fr.norsys.ApiDoc.repository.impl;

import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.model.User;
import fr.norsys.ApiDoc.repository.DocumentDao;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Properties;


@Repository
@Slf4j
public class DocumentDaoImpl implements DocumentDao {

    private static final String SELECT_DOCUMENTS = "select.documents";
    private static final String DOC_GET_ONE = "document.getOne";
    private static final String DOC_ID ="id" ;

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Resource(name = "docProperties")
    Properties properties;

    @Override
    public List<Document> getAllDocuments() {
        return jdbcTemplate.query(properties.getProperty(SELECT_DOCUMENTS),getRowMapper());
    }

    @Override
    public Optional<Document> getDocumentById(int id) {
        Optional<Document> doc=Optional.empty();
        try {
            doc = Optional.of(jdbcTemplate.queryForObject(properties.getProperty(DOC_GET_ONE), new MapSqlParameterSource(DOC_ID, id), Document::baseMapper));
        } catch (DataAccessException dataAccessException) {
            log.info("document does not exist " + id);
        }
        return doc;


    }

    @Override
    public Optional<Document> saveDocument(Document document) {
        return Optional.empty();
    }

    @Override
    public int deleteDocumentById(int id) {
        return 0;
    }

    private RowMapper<Document> getRowMapper() {
        return (rs, rowNum) -> Document.builder()
                .idDocument(rs.getInt("ID_DOCUMENT"))
                .nom(rs.getString("NOM"))
                .type(rs.getString("TYPE"))
                .dateCreation(rs.getDate("DATE_CREATION"))
                .urlDocument(rs.getString("URL_DOCUMENT"))
                .build();
    }
}
