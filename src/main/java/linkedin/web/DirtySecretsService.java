package linkedin.web;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DirtySecretsService {

    private final DirtySecretsRepository dirtySecretsRepository;

    public DirtySecretsService(DirtySecretsRepository dirtySecretsRepository) {
        this.dirtySecretsRepository = dirtySecretsRepository;
    }

    @Transactional
    public void deleteAll(List<UUID> secretIds) {
        secretIds.forEach(dirtySecretsRepository::deleteById);
    }
}
