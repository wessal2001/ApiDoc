package fr.norsys.ApiDoc.controller;

import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/api/documents")
@RequiredArgsConstructor
public class DocumentController {


    private final DocumentService documentService;

    @GetMapping
    ResponseEntity<List<Document>>getAllDocuments(){
        return ResponseEntity.ok(documentService.getAllDocuments());
    }

    @DeleteMapping("/deletedoc/{id}")
    public ResponseEntity<String> deleteDocumentById(@PathVariable("id") int id) {
        System.out.println("start controller");
        int deletedCount = documentService.deleteDocumentById(id);
        if (deletedCount > 0) {
            return new ResponseEntity<>("Document deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Document not found", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addDocument")
    public  ResponseEntity<Optional<Document>> saveDocument(@RequestParam("file") MultipartFile file )throws IOException, NoSuchAlgorithmException{
        return  ResponseEntity.ok(documentService.saveDocument(file));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable int id){
        return ResponseEntity.ok(documentService.getDocumentById(id));
    }



}
