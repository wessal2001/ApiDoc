package fr.norsys.ApiDoc.repository;

import fr.norsys.ApiDoc.model.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentDaoTest {

    @Autowired
    DocumentDao documentDao;

    @Test
    public  void shouldDeleteDocumentById(){
        Document document=Document.builder()
                .idDocument(2)
                .type("type2")
                .nom("doc2")
                .build();
        documentDao.deleteDocumentById(document.getIdDocument());
    }
}
