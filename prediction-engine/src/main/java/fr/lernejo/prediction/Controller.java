package fr.lernejo.prediction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class Controller {
    
    private Temperatures getCountryTemperature(String country) throws UnknownCountryException{
        TemperatureService temperatureService = new TemperatureService();
        ArrayList<Temperature> temperatures = new ArrayList<>();
        LocalDate now = LocalDate.now();
        Temperature temp1 = new Temperature(now.toString(), temperatureService.getTemperature(country));
        Temperature temp2 = new Temperature(now.minusDays(1).toString(), temperatureService.getTemperature(country));
        temperatures.add(temp1);
        temperatures.add(temp2);
        return new Temperatures(country, temperatures);
    }

    @GetMapping(value = "/api/temperature")
    public Object requestCountryTemperature(@RequestParam String country){
        try {
            return getCountryTemperature(country);
        }
        catch (UnknownCountryException e){
            return ResponseEntity.status(417).body("Unknown country (CODE 417)");
        }
    }
}
