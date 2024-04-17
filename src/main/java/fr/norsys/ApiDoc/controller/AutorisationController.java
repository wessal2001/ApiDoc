package fr.norsys.ApiDoc.controller;

import fr.norsys.ApiDoc.model.Autorisation;
import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.service.AutorisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/api/Autorisation")
@RequiredArgsConstructor
public class AutorisationController {

    private final AutorisationService autorisationService;

    @PostMapping()
    public ResponseEntity<Optional<Autorisation>> shareDocument(@RequestBody Autorisation autorisation) {
        return ResponseEntity.ok(autorisationService.shareDocument(autorisation));
    }
}
