package fr.norsys.ApiDoc.controller;

import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.repository.BaseTest;
import fr.norsys.ApiDoc.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = DocumentController.class)
@ExtendWith(MockitoExtension.class)
public class DocumentControllerTest extends BaseTest {

    @Autowired
    protected MockMvc mockMvc;
    @Mock
    DocumentService documentService;

    @Test
    @Rollback
    public void shouldSaveDocument() throws Exception {
        String filePath = "C:/Users/CePC/Desktop/docsStorage/tdd.txt";
        File localFile = new File(filePath);

        MultipartFile file = new MockMultipartFile(localFile.getName(), localFile.getName(),
                "text/plain", new FileInputStream(localFile));

        Date dateCreation = new Date(2024, 4, 16);

        Document document = Document.builder()
                .idDocument(11)
                .nom("tdd.txt")
                .type("txt")
                .dateCreation(dateCreation)
                .urlDocument("12ca819ce6f6addb83de938ecb79fdda90913492766e5f722b2b5304405a35be")
                .build();

        when(documentService.saveDocument(file)).thenReturn(Optional.of(document));
        mockMvc.perform(multipart("/v1/api/documents/addDocument")
                        .file((MockMultipartFile) file)
                        .param("idDocument", "11")
                        .param("nom", "tdd.txt")
                        .param("type", "txt")
                        .param("dateCreation", "2024-04-16")
                        .param("urlDocument", "12ca819ce6f6addb83de938ecb79fdda90913492766e5f722b2b5304405a35be")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
