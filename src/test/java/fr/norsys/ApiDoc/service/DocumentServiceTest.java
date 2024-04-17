package fr.norsys.ApiDoc.service;

import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.repository.BaseTest;
import fr.norsys.ApiDoc.repository.impl.DocumentDaoImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


public class DocumentServiceTest extends BaseTest {

    @Autowired
    DocumentService documentService;
    @Mock
    DocumentDaoImpl documentDao;

    @Test
    public void shouldReturnSavedDocument() throws IOException, NoSuchAlgorithmException {
        String filePath = "C:/Users/CePC/Desktop/docsStorage/tdd.txt";
        File localFile = new File(filePath);

        MultipartFile file = new MockMultipartFile(localFile.getName(), localFile.getName(),
                "text/plain", new FileInputStream(localFile));

        Date dateCreation = new Date(2024, 4, 16);

        Document document=Document.builder()
                .idDocument(11)
                .nom("tdd.txt")
                .type("txt")
                .dateCreation(dateCreation)
                .urlDocument("12ca819ce6f6addb83de938ecb79fdda90913492766e5f722b2b5304405a35be")
                .build();

        when(documentDao.saveDocument(file)).thenReturn(Optional.of(document));

        Optional<Document> documentsaved =documentService.saveDocument(file);
        assertTrue(documentsaved.isPresent());

    }

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
    @Test
    public void should_return_docs_by_date(){
        LocalDate currentDate = LocalDate.of(2024, 4, 16);
        java.util.Date date = java.util.Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Document> docs=documentService.getDocumentByDate(date);
        assertEquals(1,docs.size());

    }
    @Test
    public void should_return_empty_list_when_date_not_found(){
        LocalDate currentDate = LocalDate.of(2025, 4, 16);
        java.util.Date date = java.util.Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Document> docs=documentService.getDocumentByDate(date);
        assertEquals(0,docs.size());
    }
}
