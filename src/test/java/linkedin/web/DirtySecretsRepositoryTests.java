package linkedin.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ImportTestcontainers
public class DirtySecretsRepositoryTests {

    @Autowired
    private DirtySecretsRepository dirtySecretsRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:latest"));

    @Test
    void shouldSaveSecret() {
        var secret = new DirtySecret();
        secret.setName("Freddy");
        secret.setSecret("Did time");

        var savedSecret = dirtySecretsRepository.save(secret);
        assertNotNull(savedSecret.getId());
    }
}
