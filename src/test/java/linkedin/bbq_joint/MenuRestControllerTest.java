package linkedin.bbq_joint;

import linkedin.bbq_joint.controller.MenuRestController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@WebMvcTest(MenuRestController.class)
class MenuRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuItemRepository menuItemRepository;


    @Test
    void shouldSaveMenuItem() throws Exception {
        MenuItem menuItem = mock(MenuItem.class);
        Mockito.when(menuItemRepository.save(any(MenuItem.class)))
                .thenReturn(menuItem);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/menu-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": "00000000-0000-0000-0000-000000000001",
                                  "name": "Freddy's Ribs Special",
                                  "price": 20
                                }"""))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}