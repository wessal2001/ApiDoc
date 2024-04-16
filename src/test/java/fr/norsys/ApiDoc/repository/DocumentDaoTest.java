package fr.norsys.ApiDoc.repository;

import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.model.User;
import fr.norsys.ApiDoc.repository.impl.DocumentDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocumentDaoTest extends BaseTest{
    @Autowired
    DocumentDaoImpl documentDao;
    @Test
    public void testFindById() {
        Optional<Document> optionalDoc = documentDao.getDocumentById(1);
        assertTrue(optionalDoc.isPresent());
        assertEquals("testDoc", optionalDoc.get().getNom());
    }

}
