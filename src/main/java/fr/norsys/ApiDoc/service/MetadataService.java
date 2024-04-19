package fr.norsys.ApiDoc.service;

import fr.norsys.ApiDoc.model.Metadata;
import fr.norsys.ApiDoc.repository.impl.MetadataDaoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetadataService {
    private  final MetadataDaoImpl metadataDao;

    public Metadata saveMetadata(Metadata metadata){
        return metadataDao.saveMetadata(metadata);
    }

    public int deleteMetadata(int id) {
        return metadataDao.deleteMeatdate(id);
    }
}

