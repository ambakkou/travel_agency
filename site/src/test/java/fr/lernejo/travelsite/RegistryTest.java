package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistryTest {
    @Test
    void void_constructor() {
        Registry registry = new Registry();
        Assertions.assertThat(registry.userEmail).isEqualTo(null);
        Assertions.assertThat(registry.userName).isEqualTo(null);
        Assertions.assertThat(registry.userCountry).isEqualTo(null);
        Assertions.assertThat(registry.weatherExpectation).isEqualTo(null);
        Assertions.assertThat(registry.minimumTemperatureDistance).isEqualTo(1);
    }

    @Test
    void constructor_test() {
        Registry registry = new Registry("test@gmail.com","test","test",WeatherExpectation.WARMER,15);
        Assertions.assertThat(registry.userEmail).isEqualTo("test@gmail.com");
        Assertions.assertThat(registry.userName).isEqualTo("test");
        Assertions.assertThat(registry.userCountry).isEqualTo("test");
        Assertions.assertThat(registry.weatherExpectation).isEqualTo(WeatherExpectation.WARMER);
        Assertions.assertThat(registry.minimumTemperatureDistance).isEqualTo(15);
    }

}
