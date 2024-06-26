package fr.norsys.ApiDoc.service;

import fr.norsys.ApiDoc.model.Autorisation;
import fr.norsys.ApiDoc.repository.AutorisationDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorisationService {

    private final AutorisationDao autorisationDao;

    public Optional<Autorisation> shareDocument(Autorisation autorisation) {
        return autorisationDao.shareDocument(autorisation);
    }
    public List<Autorisation> getDocAuthorities(int idDoc,int idUser){
        return autorisationDao.getDocAuthorities(idDoc,idUser);
    }
    public List<Autorisation> getAuthorities(){
        return autorisationDao.getAllAuthorities();
    }

    public int deleteaUTORISATION(int id) {
        return autorisationDao.deleteAutorisation(id);
    }

}
