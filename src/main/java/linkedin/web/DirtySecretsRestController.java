package linkedin.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/dirty-secrets")
public class DirtySecretsRestController {

    private final DirtySecretsRepository repository;

    public DirtySecretsRestController(DirtySecretsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/count")
    public int count() {
        return this.repository.count();
    }

    //  Variante 1
    // Die Spring ResponseStatusException-Klasse
    // new ResponseStatusException(httpStatus.NOT_FOUND)
    @GetMapping("/e1/{id}")
    public DirtySecret getByIdE1(@PathVariable("id") String id) {
        return this.repository.getById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No secret found"
                ));
    }

    //  Variante 2
    // Die Annotation @ResponseStatus an Exception-Klasse
    // @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @GetMapping("/e2/{id}")
    public DirtySecret getByIdE2(@PathVariable("id") String id) {
        return this.repository.getById(id)
                .orElseThrow(() -> new NoSecretFoundWebException());
    }

    //  Variante 3
    // eigene Exception-Handler-Methode mit @ExceptionHandler
    @GetMapping("/e3/{id}")
    public DirtySecret getByIdE3(@PathVariable("id") String id) {
        return this.repository.getById(id)
                .orElseThrow(() -> new NoSecretFoundException());
    }

    @ExceptionHandler({NoSecretFoundException.class})
    public ResponseEntity<String> handleNoSecretFoundException() {
        return ResponseEntity.internalServerError().body("No secret found.");
    }
    @PostMapping
    public DirtySecret post(@RequestBody DirtySecret secret) {
        return this.repository.save(secret);
    }
}
