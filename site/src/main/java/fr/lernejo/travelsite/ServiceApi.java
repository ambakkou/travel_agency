package fr.lernejo.travelsite;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
            if(registry.userName().equals(reg.userName())){
                registration.add(registration.indexOf(reg),registry);
                return registration;
            }
        }
        registration.add(registry);
        return registration;
    }

    public ArrayList<Country> getCountries(int temperature, WeatherExpectation weatherExpectation, String livingCountry) {
        ArrayList<Country> countriesSug = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
        for(String country: this.countries){
            try {Response<Temperatures> result = predictionEngineClient.listTemperatures(country).execute();
                if (result.isSuccessful()){
                    Number mean = Float.parseFloat(df.format((result.body().temperatures().get(0).temperature().floatValue() + result.body().temperatures().get(1).temperature().floatValue())/2));
                    if(!country.equals(livingCountry)){
                        if(weatherExpectation.equals(WeatherExpectation.COLDER) && mean.intValue() < temperature)
                            countriesSug.add(new Country(country, mean));
                        else if(weatherExpectation.equals(WeatherExpectation.WARMER) && mean.intValue() > temperature)
                            countriesSug.add(new Country(country, mean));}}
            } catch (IOException e) {}
        }
        return countriesSug;
    }

    private Registry findUser(String userName){
        for (Registry registry : registration) {
            if (registry.userName().equals(userName))
                return registry;
        }
        return null;
    }

    public Object getDestinations(String userName) {
        Registry registry = findUser(userName);
        if(registry != null){
                return getCountries(registry.minimumTemperatureDistance(),registry.weatherExpectation(),registry.userCountry());
            }
        else
            return ResponseEntity.status(417).body("Unknown username (CODE 417)");
    }
}
