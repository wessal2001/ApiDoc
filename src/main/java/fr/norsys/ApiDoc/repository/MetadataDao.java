package fr.norsys.ApiDoc.repository;

import fr.norsys.ApiDoc.model.Metadata;

public interface MetadataDao {
    Metadata saveMetadata(Metadata metadata);
    int deleteMeatdate(int id);
}
