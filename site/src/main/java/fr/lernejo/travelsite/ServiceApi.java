package fr.lernejo.travelsite;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class ServiceApi {
    final ArrayList<Registry> registration;
    final PredictionEngineClient predictionEngineClient;
    final List<String> countries = new ArrayList<>();

    public ServiceApi(PredictionEngineClient predictionEngineClient) {
        this.predictionEngineClient = predictionEngineClient;
        this.registration = new ArrayList<>();
        try {
            this.countries.addAll(new String(this.getClass().getClassLoader().getResourceAsStream("countries.txt").readAllBytes(), StandardCharsets.UTF_8).lines().toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Registry> register(Registry registry){
        for(Registry reg: registration){
            if(registry.userName.equals(reg.userName)){
                registration.remove(reg);
                registration.add(registry);
                return registration;
            }}
        registration.add(registry);
        return registration;
    }

    private double getCountryTemperature(String country){
        double res = -999;
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
        try {Call<Temperatures> call = predictionEngineClient.listTemperatures(country);
            if (call != null){
                Response<Temperatures> result = call.execute();
                Temperatures body = result.body();
                if ((result.isSuccessful()) && (body !=null && body.country.equals(country))){
                    res = Double.parseDouble(df.format((body.temperatures.get(0).temperature + body.temperatures.get(1).temperature)/2));}}
            } catch (IOException e) {}
        return res;
    }

    private ArrayList<Country> getCountries(int temperature, WeatherExpectation weatherExpectation, String livingCountry) {
        ArrayList<Country> countriesSug = new ArrayList<>();
        double home = getCountryTemperature(livingCountry);
        double diff = weatherExpectation.equals(WeatherExpectation.WARMER) ? temperature + home : home - temperature;
        for(String country: this.countries){
            if(!country.equals(livingCountry)){
                double mean = getCountryTemperature(country);
                if(mean != -999){
                    if((weatherExpectation.equals(WeatherExpectation.COLDER) && mean <= diff) || (weatherExpectation.equals(WeatherExpectation.WARMER) && mean >= diff))
                        countriesSug.add(new Country(country, mean));}}}
        return countriesSug;}

    private Registry findUser(String userName){
        for (Registry registry : registration) {
            if (registry.userName.equals(userName))
                return registry;
        }
        return null;
    }

    public Object getDestinations(String userName) {
        Registry registry = findUser(userName);
        if(registry != null){
                return getCountries(registry.minimumTemperatureDistance,registry.weatherExpectation,registry.userCountry);
            }
        else
            return ResponseEntity.status(417).body("Unknown username (CODE 417)");
    }
}
