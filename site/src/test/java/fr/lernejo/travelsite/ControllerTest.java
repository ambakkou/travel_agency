package fr.lernejo.travelsite;

import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ControllerTest {
    @Test
    void register_a_new_user(@Autowired MockMvc mockMvc) throws Exception {
        String json = new Gson().toJson(new Registry("test@gmail.com","test","test",WeatherExpectation.WARMER,15));
        mockMvc
            .perform(MockMvcRequestBuilders.post("/api/inscription").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void register_a_new_user_2(@Autowired MockMvc mockMvc) throws Exception {
        Controller ctrl = new Controller(new ServiceApi(Mockito.mock(PredictionEngineClient.class)));
        Assertions.assertThat(ctrl.register(new Registry("test@gmail.com","test","test",WeatherExpectation.WARMER,15))).isEqualTo(ResponseEntity.ok(HttpStatus.OK));
    }
    @Test
    void getDestinations_unknown_username(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/travels?userName=Amine"))
            .andExpect(MockMvcResultMatchers.status().isExpectationFailed())
            .andExpect(MockMvcResultMatchers.content().string("Unknown username (CODE 417)"));
    }
    @Test
    void getDestinations_test(@Autowired MockMvc mockMvc) throws Exception {
        String json = new Gson().toJson(new Registry("test@gmail.com","test","test",WeatherExpectation.WARMER,15));
        mockMvc
            .perform(MockMvcRequestBuilders.post("/api/inscription").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/travels?userName=test"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
