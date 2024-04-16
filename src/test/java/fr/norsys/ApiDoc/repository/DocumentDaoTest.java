package fr.norsys.ApiDoc.repository;



import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.repository.impl.DocumentDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocumentDaoTest extends BaseTest{
    @Autowired
    DocumentDaoImpl documentDao;
    @Test
    public void should_return_doc_by_id() {
        Optional<Document> optionalDoc = documentDao.getDocumentById(1);
        assertTrue(optionalDoc.isPresent());
    }
    @Test
    public void should_return_docs_by_name(){
        List<Document> docs=documentDao.getDocumentByName("testDoc");
        assertEquals(1,docs.size());
    }
    @Test
    @Rollback
    public void shouldReturnSavedDocument() throws IOException, NoSuchAlgorithmException {
        String filePath = "C:/Users/CePC/Desktop/stage_PFE_formation/keep.txt";
        File localFile = new File(filePath);

        MultipartFile file = new MockMultipartFile(localFile.getName(), localFile.getName(),
                "text/plain", new FileInputStream(localFile));


        Optional<Document> document=documentDao.saveDocument(file);
        assertTrue(document.isPresent());
    }
    @Test
    public void should_return_empty_list_when_name_not_found(){
        List<Document> docs=documentDao.getDocumentByName("testDocxxx");
        assertEquals(0,docs.size());
    }
    @Test
    public void should_return_docs_by_type(){
        List<Document> docs=documentDao.getDocumentByType("type");
        assertEquals(1,docs.size());

    }
    @Test
    public void should_return_empty_list_when_type_not_found(){
        List<Document> docs=documentDao.getDocumentByType("typexx");
        assertEquals(0,docs.size());
    }
}
