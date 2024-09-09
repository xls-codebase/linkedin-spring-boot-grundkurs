package linkedin.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dirty-secrets")
public class DirtySecretsRestController {

    private final DirtySecretsRepository repository;
    private final DirtySecretsService dirtySecretsService;

    public DirtySecretsRestController(DirtySecretsRepository repository, DirtySecretsService dirtySecretsService) {
        this.repository = repository;
        this.dirtySecretsService = dirtySecretsService;
    }

    @GetMapping
    public Iterable<DirtySecret> get() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public DirtySecret getById(@PathVariable String id) {
        return this.repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Found nothing."));
    }

    @PostMapping
    public DirtySecret post(@RequestBody DirtySecret secret) {
        var savedSecret = this.repository.save(secret);
        return savedSecret;
    }

    @DeleteMapping
    public void delete(@RequestBody List<UUID> secretIds) {
        dirtySecretsService.deleteAll(secretIds);
    }
}
