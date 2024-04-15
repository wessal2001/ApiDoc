package fr.norsys.ApiDoc.repository;

import fr.norsys.ApiDoc.model.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentDao {
    List<Document> getAllDocuments();

    Optional<Document> getDocumentById(int id);

    Optional<Document> saveDocument(Document document);

    int deleteDocumentById(int id);

}
