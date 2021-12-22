package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ServiceApiTest {
    @Autowired
    PredictionEngineClient predictionEngineClient = Mockito.mock(PredictionEngineClient.class);
    final Controller ctrl = new Controller(new ServiceApi(predictionEngineClient));
    @Test
    void register_a_new_user() {
        Registry user = new Registry("user5","user5@yahoo.fr","France",WeatherExpectation.WARMER,1);
        ctrl.serviceApi.register(user);
        Assertions.assertThat(ctrl.serviceApi.registration).contains(user);
    }

    @Test
    void register_multiple_users() {
        Registry user = new Registry("user","user@yahoo.fr","France",WeatherExpectation.WARMER,1);
        Registry user2 = new Registry("user2","user2@yahoo.fr","France",WeatherExpectation.COLDER,3);
        Assertions.assertThat(ctrl.serviceApi.register(user)).contains(user);
        Assertions.assertThat(ctrl.serviceApi.register(user2)).contains(user2);
    }

    @Test
    void register_no_duplicate() {
        Registry user = new Registry("user","user@yahoo.fr","France",WeatherExpectation.WARMER,1);
        ctrl.serviceApi.register(user);
        Assertions.assertThat(ctrl.serviceApi.register(user).size()).isEqualTo(1);
    }

    @Test
    void get_destinations_unknown_test(){
        Assertions.assertThat(ctrl.serviceApi.getDestinations("test")).isInstanceOf(ResponseEntity.class);
    }

    @Test
    void get_destinations_test(){
        Registry user = new Registry("usertest@test.fr","usertest","France",WeatherExpectation.WARMER,1);
        ctrl.register(user);
        Assertions.assertThat(ctrl.getDestinations("usertest")).isInstanceOf(ArrayList.class);
    }

}
