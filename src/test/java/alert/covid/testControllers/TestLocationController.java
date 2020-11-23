package alert.covid.testControllers;

import alert.covid.models.Location;
import alert.covid.repositories.LocationRepository;
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
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.data.repository.CrudRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class TestLocationController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationRepository locationRepository;

    @Test
    @DisplayName("GET /locations - FOUND")
    public void getLocations() throws Exception {
        this.mockMvc.perform(get("/locations"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /locations/1 - FOUND")
    public void getOneLocation() throws Exception {
        Location mockLocation = new Location();
        mockLocation.setLocation_id(1);

        doReturn(Optional.of(mockLocation)).when(locationRepository).findById((long) 1);

        mockMvc.perform(get("/locations/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect((ResultMatcher) jsonPath("$.id", is(1)));
    }

    @Test
    @DisplayName("GET /location/1 - NOT FOUND")
    public void cannotGetLocation() throws Exception {
        doReturn(Optional.empty()).when(locationRepository).findById((long) 1);

        mockMvc.perform(get("/locations/{id}", 1))
                .andExpect(status().isNotFound());
    }

}
