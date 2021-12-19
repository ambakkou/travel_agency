package fr.lernejo.prediction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class Controller {
    final TemperatureService temperatureService = new TemperatureService();

    @GetMapping(value = "/api/temperature")
    public Object getTemperature(@RequestParam String country){
        Temperatures temperatures = new Temperatures(country,new ArrayList<Temperature>());
        LocalDate now = LocalDate.now();
        try {
            temperatures.temperatures().add(new Temperature(now.minusDays(1).toString(),temperatureService.getTemperature(country)));
            temperatures.temperatures().add(new Temperature(now.minusDays(2).toString(),temperatureService.getTemperature(country)));
        }catch (UnknownCountryException e){
            return ResponseEntity.status(417).body("Unknown country (CODE 417)");
        }
        return temperatures;
    }
}
