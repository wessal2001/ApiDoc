package fr.norsys.ApiDoc.service;

import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.repository.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DocumentServiceTest extends BaseTest {
    @Autowired
    DocumentService documentService;
    @Test
     void should_return_doc_when_found(){
        Document doc = documentService.getDocumentById(1);
        assertEquals("testDoc",doc.getNom());
     }
    @Test
    public void should_return_docs_by_name(){
        List<Document> docs=documentService.getDocumentByName("testDoc");
        assertEquals(1,docs.size());

    }
    @Test
    public void should_return_empty_list_when_name_not_found(){
        List<Document> docs=documentService.getDocumentByName("testDocxxx");
        assertEquals(0,docs.size());
    }
    @Test
    public void should_return_docs_by_type(){
        List<Document> docs=documentService.getDocumentByType("type");
        assertEquals(1,docs.size());

    }
    @Test
    public void should_return_empty_list_when_type_not_found(){
        List<Document> docs=documentService.getDocumentByType("typexx");
        assertEquals(0,docs.size());
    }
}
