package fr.lernejo.travelsite;

public class Country {
    public final String country;
    public final double temperature;

    public Country(String country, double temperature) {
        this.country = country;
        this.temperature = temperature;
    }

    public Country() {
        this.country = null;
        this.temperature = 1;
    }
}
