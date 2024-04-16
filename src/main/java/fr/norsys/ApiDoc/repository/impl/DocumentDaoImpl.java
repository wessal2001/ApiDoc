package fr.norsys.ApiDoc.repository.impl;

import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.repository.DocumentDao;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Repository
public class DocumentDaoImpl implements DocumentDao {

    private static final String SELECT_DOCUMENTS = "select.documents";
    private static final String DELETE_DOCUMENTS = "delete.document";
    private static final String INSERT_DOCUMENT="insert.document";

    @Value("${file.storage.location}")
    private String storageLocation;

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
        return Optional.empty();
    }

    @Override
    public String getHashFile(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] buffer = new byte[8192];
        int bytesRead;
        try (InputStream inputStream = file.getInputStream()) {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }
        byte[] hashBytes = digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }


    @Override
    public Optional<Document> saveDocument(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        Document document = new Document();

        String fileName = file.getOriginalFilename();
        int lastDotIndex = fileName.lastIndexOf('.');
        String extension = fileName.substring(lastDotIndex + 1);

        String absoluteFilePath = storageLocation + File.separator + fileName;
        File destinationFile = new File(absoluteFilePath);

        Files.copy(file.getInputStream(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        BasicFileAttributes attributes = Files.readAttributes(destinationFile.toPath(), BasicFileAttributes.class);
        Date creationDate = new Date(attributes.creationTime().toMillis());

        document.setNom(fileName);
        document.setType(extension);
        document.setDateCreation(creationDate);
        document.setUrlDocument(getHashFile(file));

        jdbcTemplate.update(properties.getProperty(INSERT_DOCUMENT), getSqlParameterSource(document));

        return Optional.of(document);
    }


    @Override
    public int deleteDocumentById(int id) {
        System.out.println("start repo");
        return	jdbcTemplate.update(properties.getProperty(DELETE_DOCUMENTS),new MapSqlParameterSource().addValue("document_id", id));
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
    private MapSqlParameterSource getSqlParameterSource(final Document document) {
        return new MapSqlParameterSource()
                .addValue("nom",document.getNom())
                .addValue("type",document.getType())
                .addValue("date_creation",document.getDateCreation())
                .addValue("url_document",document.getUrlDocument());
    }


}
