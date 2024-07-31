package linkedin.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@WebMvcTest(DirtySecretsRestController.class)
class DirtySecretsRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirtySecretsRepository repository;


    @Test
    void shouldSaveSecret() throws Exception  {
        DirtySecret secret = mock(DirtySecret.class);
        Mockito.when(repository.save(any(DirtySecret.class)))
                .thenReturn(secret);
        Mockito.when(secret.getId()).thenReturn("test");
        mockMvc
            .perform(MockMvcRequestBuilders.post("/dirty-secrets")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n \"name\": \"Freddy\", \n\"secret\": \"Killed an elderly couple and did time.\"\n}"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());

    }
}