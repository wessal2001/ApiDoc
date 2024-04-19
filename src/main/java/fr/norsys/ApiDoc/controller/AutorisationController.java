package fr.norsys.ApiDoc.controller;

import fr.norsys.ApiDoc.model.Autorisation;
import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.service.AutorisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/doc-authorities/{idDoc}/{idUser}")
    public ResponseEntity<List<Autorisation>> getDocAuthorities(@PathVariable int idDoc,@PathVariable int idUser) {
        return ResponseEntity.ok(autorisationService.getDocAuthorities(idDoc,idUser));
    }
    @GetMapping
    public ResponseEntity<List<Autorisation>> getAuthorities() {
        return ResponseEntity.ok(autorisationService.getAuthorities());
    }
}
