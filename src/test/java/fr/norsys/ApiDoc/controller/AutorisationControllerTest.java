package fr.norsys.ApiDoc.controller;

import fr.norsys.ApiDoc.model.Autorisation;
import fr.norsys.ApiDoc.repository.BaseTest;
import fr.norsys.ApiDoc.service.AutorisationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AutorisationControllerTest extends BaseTest {

    @Mock
    private AutorisationService autorisationService;

    @InjectMocks
    private AutorisationController autorisationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShareDocument() {
        Autorisation autorisation = new Autorisation(1,1,"ReadOnly");
        when(autorisationService.shareDocument(autorisation)).thenReturn(Optional.of(autorisation));

        ResponseEntity<Optional<Autorisation>> response = autorisationController.shareDocument(autorisation);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
