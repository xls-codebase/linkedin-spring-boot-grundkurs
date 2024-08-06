package linkedin.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;


@WebMvcTest(DirtySecretsRestController.class)
class DirtySecretsRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirtySecretsRepository repository;


    @Test
    void shouldSaveSecret() throws Exception  {
        DirtySecret secret = mock(DirtySecret.class);
        UUID testId = UUID.randomUUID();
        Mockito.when(repository.save(any(DirtySecret.class)))
                .thenReturn(secret);
        Mockito.when(secret.getId()).thenReturn(testId);
        mockMvc
            .perform(MockMvcRequestBuilders.post("/dirty-secrets")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n \"name\": \"Freddy\", \n\"secret\": \"Killed an elderly couple and did time.\"\n}"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());

    }
}