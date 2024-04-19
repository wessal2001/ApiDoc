package fr.norsys.ApiDoc.repository.impl;

import fr.norsys.ApiDoc.model.Autorisation;
import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.repository.DocumentDao;
import fr.norsys.ApiDoc.service.AutorisationService;
import fr.norsys.ApiDoc.service.UserService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Repository
@RequiredArgsConstructor
@Slf4j
public class DocumentDaoImpl implements DocumentDao {

    private static final String SELECT_DOCUMENTS = "select.documents";

    private static final String DOC_GET_ONE = "document.getOne";
    private static final String DOC_GET_ONE_BY_NAME = "document.getByName";

    private static final String DOCUMENT_ID ="idDocument" ;
    private static final String DOC_ID ="id" ;
    private static final String DOC_NAME ="nom" ;
    private static final String DOC_TYPE ="type" ;
    private static final String DOC_DATE ="date" ;

    private static final String DELETE_DOCUMENTS = "delete.document";
    private static final String INSERT_DOCUMENT="insert.document";
    private static final String  DOC_GET_ONE_BY_TYPE = "document.getByType";
    private static final String  DOC_GET_ONE_BY_DATE = "document.getByDate";
    private static final String  DOC_GET_BY_USER = "document.getUserDocs";
    private static final String  DOC_GET_MANY = "document.getByManyCriteria";
    private static final String  DOCUMENT_Id = "ID_DOCUMENT";
    private static final String  USER_Id = "iduser";

    @Value("${file.storage.location}")
    private  String storageLocation;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Resource(name = "docProperties")
    private final Properties properties;
    private final AutorisationService autorisationService;
    private final UserService userService;
    @Override
    public List<Document> getAllDocuments() {
        return jdbcTemplate.query(properties.getProperty(SELECT_DOCUMENTS),Document::baseMapper);
    }

    @Override
    public Optional<Document> getDocumentById(int id) {
        Optional<Document> doc=Optional.empty();
        try {
            doc = Optional.ofNullable(jdbcTemplate.queryForObject(properties.getProperty(DOC_GET_ONE), new MapSqlParameterSource(DOC_ID, id), Document::baseMapper));
        } catch (DataAccessException dataAccessException) {
            log.info("document does not exist " + id);
        }
        return doc;
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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Files.copy(file.getInputStream(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        BasicFileAttributes attributes = Files.readAttributes(destinationFile.toPath(), BasicFileAttributes.class);
        Date creationDate = new Date(attributes.creationTime().toMillis());
        document.setNom(fileName);
        document.setType(extension);
        document.setDateCreation(creationDate);
        document.setUrlDocument(getHashFile(file));
        jdbcTemplate.update(properties.getProperty(INSERT_DOCUMENT), getSqlParameterSource(document), keyHolder, new String[]{DOCUMENT_Id});
        if (keyHolder.getKey() != null) {
            document.setIdDocument(keyHolder.getKey().intValue());
        }
        //autorisationService.shareDocument(new Autorisation((int) userService.findUserByUdsername("admin23").getIdUser(),document.getIdDocument(),"read"));
        //autorisationService.shareDocument(new Autorisation((int) userService.findUserByUdsername("admin23").getIdUser(),document.getIdDocument(),"write"));
        return Optional.of(document);
    }
    @Override
    public int deleteDocumentById(int id) {
        return	jdbcTemplate.update(properties.getProperty(DELETE_DOCUMENTS),new MapSqlParameterSource().addValue("document_id", id));

    }
    public List<Document> getDocumentByName(String name){
        List<Document> docs=new ArrayList<>();
        try {
            docs = jdbcTemplate.query(properties.getProperty(DOC_GET_ONE_BY_NAME), new MapSqlParameterSource(DOC_NAME, name), Document::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.info("document does not exist " + name);
        }
        return docs;
    }

    @Override
    public List<Document> getDocumentByType(String type) {
        List<Document> docs=new ArrayList<>();
        try {
            docs = jdbcTemplate.query(properties.getProperty(DOC_GET_ONE_BY_TYPE), new MapSqlParameterSource(DOC_TYPE, type), Document::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.info("document does not exist " + type);
        }
        return docs;    }

    @Override
    public List<Document> getDocumentByDate(Date date) {
        List<Document> docs=new ArrayList<>();
        try {
            docs = jdbcTemplate.query(properties.getProperty(DOC_GET_ONE_BY_DATE), new MapSqlParameterSource(DOC_DATE, date), Document::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.info("document does not exist " + date);
        }
        return docs;        }

    @Override
    public List<Document> getDocumentsByCriteria(String nom, String type, Date date, Map<String, String> metadata) throws ParseException {
        if(date!=null){
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String result = outputDateFormat.format(date);
            date=outputDateFormat.parse(result);
        }
        if(type==null)
            type="%";
        if(nom==null)
            nom="%";
        Document doc = new Document(0, nom, type,date, "%",null);
        List<Document> docs=new ArrayList<>();
        try {
            MapSqlParameterSource parameterSource = getSqlParameterSource(doc);
            docs = jdbcTemplate.query(properties.getProperty(DOC_GET_MANY), parameterSource, Document::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.info("documents don't not exist " );
        }

        return docs;
    }

    @Override
    public List<Document> getUserDocs(long idUser) {
        List<Document> docs=new ArrayList<>();
        try {
            docs = jdbcTemplate.query(properties.getProperty(DOC_GET_BY_USER), new MapSqlParameterSource(USER_Id, idUser), Document::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.info("document does not exist " );
        }
        return docs;        }


    private MapSqlParameterSource getSqlParameterSource(final Document document) {
        return new MapSqlParameterSource()
                .addValue("nom",document.getNom())
                .addValue("type",document.getType())
                .addValue("date_creation",document.getDateCreation())
                .addValue("url_document",document.getUrlDocument());
    }


}
