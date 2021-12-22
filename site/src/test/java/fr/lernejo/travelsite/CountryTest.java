package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {
    @Test
    void void_constructor() {
        Country country = new Country();
        Assertions.assertThat(country.country).isEqualTo(null);
        Assertions.assertThat(country.temperature).isEqualTo(1);
    }

    @Test
    void constructor_test() {
        Country country = new Country("France",10);
        Assertions.assertThat(country.country).isEqualTo("France");
        Assertions.assertThat(country.temperature).isEqualTo(10);
    }

}
