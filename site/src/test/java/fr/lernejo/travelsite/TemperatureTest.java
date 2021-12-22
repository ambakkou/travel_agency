package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureTest {
    @Test
    void void_constructor() {
        Temperature temperature = new Temperature();
        Assertions.assertThat(temperature.date).isEqualTo(null);
        Assertions.assertThat(temperature.temperature).isEqualTo(0.0);
    }

    @Test
    void constructor_test() {
        Temperature temperature = new Temperature("22/12/2022",5);
        Assertions.assertThat(temperature.date).isEqualTo("22/12/2022");
        Assertions.assertThat(temperature.temperature).isEqualTo(5);
    }

}
