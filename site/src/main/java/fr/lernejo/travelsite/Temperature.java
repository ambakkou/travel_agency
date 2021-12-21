package fr.lernejo.travelsite;

public record Temperature(String date, Number temperature) {

    public Temperature(String date, Number temperature) {
        this.date = date;
        this.temperature = temperature;
    }
    public Temperature(){
        this(null,1);
    }
}
