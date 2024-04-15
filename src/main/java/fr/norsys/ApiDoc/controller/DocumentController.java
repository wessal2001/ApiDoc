package fr.norsys.ApiDoc.controller;

import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping
    public List<Document> getAllDocuments(){
        return documentService.getAllDocuments();
    }


}
