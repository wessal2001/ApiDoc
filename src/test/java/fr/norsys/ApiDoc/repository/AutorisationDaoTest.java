package fr.norsys.ApiDoc.repository;

import fr.norsys.ApiDoc.model.Autorisation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutorisationDaoTest extends BaseTest{

    @Autowired
    AutorisationDao autorisationDao;

    @Test
    public void shouldReturnSavedAutorisation(){
        Autorisation autorisation = new Autorisation(1,1,"lecture et ecriture");
        autorisationDao.shareDocument(autorisation);
        assertTrue(Optional.of(autorisation).isPresent());
    }


}
