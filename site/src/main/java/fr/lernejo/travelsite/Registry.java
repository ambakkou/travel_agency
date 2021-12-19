package fr.lernejo.travelsite;

public record Registry(String userEmail, String userName, String userCountry, WeatherExpectation weatherExpectation, int minimumTemperatureDistance) {

    public Registry(String userEmail, String userName, String userCountry, WeatherExpectation weatherExpectation, int minimumTemperatureDistance) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userCountry = userCountry;
        this.weatherExpectation = weatherExpectation;
        this.minimumTemperatureDistance = minimumTemperatureDistance;
    }
}
