package fr.norsys.ApiDoc.controller;

import fr.norsys.ApiDoc.model.Autorisation;
import fr.norsys.ApiDoc.model.Document;
import fr.norsys.ApiDoc.service.AutorisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public List<Autorisation> getDocAuthorities(@PathVariable int idDoc, @PathVariable int idUser) {
        return ResponseEntity.ok(autorisationService.getDocAuthorities(idDoc,idUser)).getBody();
    }
    @GetMapping
    public ResponseEntity<List<Autorisation>> getAuthorities() {
        return ResponseEntity.ok(autorisationService.getAuthorities());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDocumentById(@PathVariable("id") int id) {
        System.out.println("start controller");
        int deletedCount = autorisationService.deleteaUTORISATION(id);
        if (deletedCount > 0) {
            return new ResponseEntity<>("autorisation deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("autorisation not found", HttpStatus.NOT_FOUND);
        }
    }
}
