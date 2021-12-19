package fr.lernejo.travelsite;

public record Country(String country, Number temperature) {
    public Country(String country, Number temperature) {
        this.country = country;
        this.temperature = temperature;
    }
}
