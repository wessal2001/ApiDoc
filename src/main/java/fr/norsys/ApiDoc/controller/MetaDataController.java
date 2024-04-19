package fr.norsys.ApiDoc.controller;

import fr.norsys.ApiDoc.model.Metadata;
import fr.norsys.ApiDoc.service.MetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/metadata")
@RequiredArgsConstructor
public class MetaDataController {
    private final MetadataService metadataService;
    @PostMapping
    ResponseEntity<Metadata> addMetaData(@RequestBody Metadata metadata){
        metadataService.saveMetadata(metadata);
        return  ResponseEntity.ok(metadata);
    }

    @DeleteMapping("/deletedata/{id}")
    public ResponseEntity<String> deleteMetadata(@PathVariable("id") int id) {
        int deletedCount = metadataService.deleteMetadata(id);
        if (deletedCount > 0) {
            return new ResponseEntity<>("Meta deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Meta not found", HttpStatus.NOT_FOUND);
        }
    }

}
