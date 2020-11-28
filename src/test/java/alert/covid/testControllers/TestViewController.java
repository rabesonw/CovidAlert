package alert.covid.testControllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class TestViewController
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TestViewController {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test success for not logged-in user to access the login page
     * @throws Exception
     */
    @Test
    @DisplayName("GET /login or /index - Found - Not logged in")
    public void getLogin() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Log in")));
    }

    /**
     * Test success for not logged-in user to access the register page
     * @throws Exception
     */
    @Test
    @DisplayName("GET /register - Found")
    public void getRegister() throws Exception {
        this.mockMvc.perform(get("/register")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create a new user")));
    }
}
