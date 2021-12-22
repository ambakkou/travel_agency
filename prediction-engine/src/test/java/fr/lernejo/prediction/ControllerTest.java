package fr.lernejo.prediction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ControllerTest {

    @Test
    void temperature_respond_OK(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/temperature?country=France"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void temperature_respond_error(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/temperature?country=Morocco"))
            .andExpect(MockMvcResultMatchers.status().isExpectationFailed())
            .andExpect(MockMvcResultMatchers.content().string("Unknown country (CODE 417)"));
    }

}
