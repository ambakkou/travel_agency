package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TemperaturesTest {

    @Test
    void void_constructor() {
        Temperatures temperatures = new Temperatures();
        Assertions.assertThat(temperatures.country).isEqualTo(null);
        Assertions.assertThat(temperatures.temperatures).isEqualTo(null);
    }

    @Test
    void constructor_test() {
        Temperatures temperatures = new Temperatures("test",new ArrayList<Temperature>());
        Assertions.assertThat(temperatures.country).isEqualTo("test");
        Assertions.assertThat(temperatures.temperatures).isInstanceOf(ArrayList.class);
    }
}
