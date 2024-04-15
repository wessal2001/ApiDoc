package fr.norsys.ApiDoc.service;

import fr.norsys.ApiDoc.exception.NotFoundException;
import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.repository.DocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    @Autowired
    DocumentDao documentDao;

    public List<Document> getAllDocuments(){
        return documentDao.getAllDocuments();
    }
    public Document getDocumentById(int id){ return  documentDao.getDocumentById(id).orElseThrow(
            () -> {throw new NotFoundException("No such document");
            }
    );}

}
