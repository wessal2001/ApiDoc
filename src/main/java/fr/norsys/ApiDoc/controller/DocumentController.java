package fr.norsys.ApiDoc.controller;

import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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
    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<Document>> getDocumentByName(@PathVariable String name){
        return ResponseEntity.ok(documentService.getDocumentByName(name));
    }
    @GetMapping("/getByType/{type}")
    public ResponseEntity<List<Document>> getDocumentByType(@PathVariable String type){
        return ResponseEntity.ok(documentService.getDocumentByType(type));
    }
    @GetMapping("/getByDate")
    public ResponseEntity<List<Document>> getDocumentByType(@RequestBody Map<String, String> requestBody){
        String dateString = requestBody.get("date");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(documentService.getDocumentByDate(date));    }
@GetMapping("/getByCriteria")
    public ResponseEntity<List<Document>> getDocumentByCriteria(@RequestBody Document criteria) throws ParseException {
    return ResponseEntity.ok(documentService.getDocumentsByCriteria(criteria.getNom(),criteria.getType(),criteria.getDateCreation(),new HashMap<>()));

}

}
