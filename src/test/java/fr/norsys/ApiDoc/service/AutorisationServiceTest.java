package fr.norsys.ApiDoc.service;

import fr.norsys.ApiDoc.model.Autorisation;
import fr.norsys.ApiDoc.repository.BaseTest;
import fr.norsys.ApiDoc.repository.impl.AutorisationDaoImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AutorisationServiceTest extends BaseTest {
    @Autowired
    AutorisationService autorisationService;

    @Mock
    AutorisationDaoImpl autorisationDao;

    @Test
    public  void shouldReturnSharedAutorisation(){
        Autorisation autorisation=new Autorisation(1,1,"readOnly");
        when(autorisationDao.shareDocument(autorisation)).thenReturn(Optional.of(autorisation));
        Optional<Autorisation> autorisatioShared=autorisationService.shareDocument(autorisation);
        assertTrue(autorisatioShared.isPresent());
    }



}
