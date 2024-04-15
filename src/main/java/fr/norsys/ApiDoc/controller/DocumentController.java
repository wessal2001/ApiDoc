package fr.norsys.ApiDoc.controller;

import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/api/documents")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping
    public List<Document> getAllDocuments(){
        return documentService.getAllDocuments();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable int id){
        return ResponseEntity.ok(documentService.getDocumentById(id));
    }


}
