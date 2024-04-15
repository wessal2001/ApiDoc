package fr.norsys.ApiDoc.service;

import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.repository.DocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    @Autowired
    DocumentDao documentDao;

    public List<Document> getAllDocuments(){
        return documentDao.getAllDocuments();
    }

}
