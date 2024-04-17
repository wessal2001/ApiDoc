package fr.norsys.ApiDoc.repository;

import fr.norsys.ApiDoc.model.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DocumentDao {
    List<Document> getAllDocuments();

    Optional<Document> getDocumentById(int id);

    Optional<Document> saveDocument(MultipartFile file) throws IOException, NoSuchAlgorithmException;
    String getHashFile(MultipartFile  file) throws IOException, NoSuchAlgorithmException;

    int deleteDocumentById(int id);
    List<Document> getDocumentByName(String name);

    List<Document> getDocumentByType(String type);
    List<Document> getDocumentByDate(Date date);
    List<Document> getDocumentsByCriteria(String nom, String type, Date date,Map<String,String> metadata) throws ParseException;

}
