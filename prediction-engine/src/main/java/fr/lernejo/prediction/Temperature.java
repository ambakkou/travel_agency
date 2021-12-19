package fr.lernejo.prediction;

public record Temperature(String date, Number temperature) {
    public Temperature(String date, Number temperature) {
        this.date = date;
        this.temperature = temperature;
    }
}
