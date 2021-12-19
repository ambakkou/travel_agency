package fr.lernejo.prediction;

import java.util.ArrayList;

public record Temperatures(String country, ArrayList<Temperature> temperatures) {
    public Temperatures(String country, ArrayList<Temperature> temperatures) {
        this.country = country;
        this.temperatures = temperatures;
    }
}
