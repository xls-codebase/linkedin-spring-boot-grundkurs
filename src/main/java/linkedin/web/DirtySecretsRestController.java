package linkedin.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dirty-secrets")
public class DirtySecretsRestController {

    private DirtySecretsRepository repository;

    public DirtySecretsRestController(DirtySecretsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/count")
    public int count() {
        return this.repository.count();
    }

    @GetMapping("/{id}")
    public DirtySecret getById(@PathVariable("id") String id) {
        return this.repository.getById(id).orElseThrow();
    }

    @PostMapping
    public DirtySecret post(@RequestBody DirtySecret secret) {
        return this.repository.save(secret);
    }
}
