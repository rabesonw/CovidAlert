package alert.covid.testControllers;

import alert.covid.models.User;
import alert.covid.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class TestUserController
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TestUserController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    /**
     * Test success for GET mapping of users
     * @throws Exception
     */
    @Test
    @DisplayName("GET /users - FOUND")
    public void getUsers() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    /**
     * Test success for GET mapping of one user
     * @throws Exception
     */
    @Test
    @DisplayName("GET /users/1 - FOUND")
    public void getOneUser() throws Exception {
        User mockUser = new User();
          mockUser.setUser_id(1);

        doReturn(Optional.of(mockUser)).when(userRepository).findById((long) 1);

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect((ResultMatcher) jsonPath("$.id", is(1)));
    }

    /**
     * Test fail for GET mapping of one user
     * @throws Exception
     */
    @Test
    @DisplayName("GET /users/1 - NOT FOUND")
    public void cannotGetUser() throws Exception {
        doReturn(Optional.empty()).when(userRepository).findById((long) 1);

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isNotFound());
    }

}
