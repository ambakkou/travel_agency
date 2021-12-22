package fr.lernejo.travelsite;

public class Registry {
    public final String userEmail;
    public final String userName;
    public final String userCountry;
    public final WeatherExpectation weatherExpectation;
    public final int minimumTemperatureDistance;

    public Registry(String userEmail, String userName, String userCountry, WeatherExpectation weatherExpectation, int minimumTemperatureDistance) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userCountry = userCountry;
        this.weatherExpectation = weatherExpectation;
        this.minimumTemperatureDistance = minimumTemperatureDistance;
    }

    public Registry() {
        this.userEmail = null;
        this.userName = null;
        this.userCountry = null;
        this.weatherExpectation = null;
        this.minimumTemperatureDistance = 1;
    }
}
