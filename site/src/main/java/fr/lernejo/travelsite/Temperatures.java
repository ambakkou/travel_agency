package fr.lernejo.travelsite;

import java.util.List;

public record Temperatures(String country, List<Temperature> temperatures) {

    public Temperatures(String country, List<Temperature> temperatures) {
        this.country = country;
        this.temperatures = temperatures;
    }

    public Temperatures() {
        this(null,null);
    }
}
