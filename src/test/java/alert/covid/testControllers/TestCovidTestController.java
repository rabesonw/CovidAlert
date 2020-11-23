package alert.covid.testControllers;

import alert.covid.models.CovidTest;
import alert.covid.models.User;
import alert.covid.repositories.CovidTestRepository;
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

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestCovidTestController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CovidTestRepository covidTestRepository;

    @Test
    @DisplayName("GET /covid_test - FOUND")
    public void getUsers() throws Exception {
        this.mockMvc.perform(get("/covid_test"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /covid_test/1 - FOUND")
    public void getOneUser() throws Exception {
        CovidTest mockCovidTest = new CovidTest();
        mockCovidTest.setTest_id(1);

        doReturn(Optional.of(mockCovidTest)).when(covidTestRepository).findById((long) 1);

        mockMvc.perform(get("/covid_test/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect((ResultMatcher) jsonPath("$.id", is(1)));
    }

    @Test
    @DisplayName("GET /covid_test/1 - NOT FOUND")
    public void cannotGetUser() throws Exception {
        doReturn(Optional.empty()).when(covidTestRepository).findById((long) 1);

        mockMvc.perform(get("/covid_test/{id}", 1))
                .andExpect(status().isNotFound());
    }

}
