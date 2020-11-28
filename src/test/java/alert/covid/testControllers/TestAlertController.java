package alert.covid.testControllers;

import alert.covid.models.Alert;
import alert.covid.repositories.AlertRepository;
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

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class TestAlertController
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TestAlertController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlertRepository alertRepository;

    /**
     * Test success for GET mapping of locations
     * @throws Exception
     */
    @Test
    @DisplayName("GET /alerts - FOUND")
    public void getAlerts() throws Exception {
        this.mockMvc.perform(get("/alerts"))
                .andExpect(status().isOk());
    }

    /**
     * Test success for GET mapping of one location
     * @throws Exception
     */
    @Test
    @DisplayName("GET /alerts/1 - FOUND")
    public void getOneAlert() throws Exception {
        Alert mockAlert = new Alert();
        mockAlert.setAlert_id(1);

        doReturn(Optional.of(mockAlert)).when(alertRepository).findById((long) 1);

        mockMvc.perform(get("/alerts/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect((ResultMatcher) jsonPath("$.id", is(1)));
    }

    /**
     * Test fail for GET mapping of one location
     * @throws Exception
     */
    @Test
    @DisplayName("GET /alerts/1 - NOT FOUND")
    public void cannotGetAlert() throws Exception {
        doReturn(Optional.empty()).when(alertRepository).findById((long) 1);

        mockMvc.perform(get("/alerts/{id}", 1))
                .andExpect(status().isNotFound());
    }

}
