package fr.norsys.ApiDoc.repository;

import fr.norsys.ApiDoc.model.Autorisation;
import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.model.User;

import java.util.List;
import java.util.Optional;

public interface AutorisationDao {
    Optional<Autorisation> shareDocument(Autorisation autorisation);
    List<Autorisation> getDocAuthorities(int idDoc,int idUser);
    List<Autorisation> getAllAuthorities();
}
