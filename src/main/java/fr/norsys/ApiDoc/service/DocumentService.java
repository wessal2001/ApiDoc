package fr.norsys.ApiDoc.service;

import fr.norsys.ApiDoc.exception.NotFoundException;
import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.repository.DocumentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentDao documentDao;

    public List<Document> getAllDocuments() {
        return documentDao.getAllDocuments();
    }
    public Document getDocumentById(int id){ return  documentDao.getDocumentById(id).orElseThrow(
            () -> {throw new NotFoundException("No such document");
            }
    );}

    public int deleteDocumentById(int id) {
        return documentDao.deleteDocumentById(id);
    }
    public Optional<Document> saveDocument(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        return documentDao.saveDocument(file);
    }
    public List<Document> getDocumentByName(String name){
        return documentDao.getDocumentByName(name);
    }


    public List<Document> getDocumentByType(String type) {
        return documentDao.getDocumentByType(type);
    }
    public List<Document> getDocumentByDate(Date date) {
        return documentDao.getDocumentByDate(date);
    }
}
