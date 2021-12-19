package fr.lernejo.travelsite;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
public class Controller {

    final ArrayList<Registry> registration =  new ArrayList<>();

    @PostMapping(value = "/api/inscription")
    public ArrayList<Registry> register(@RequestBody Registry registry){
        registration.add(registry);
        return registration;
    }

    @GetMapping(value = "/api/travels")
    public ArrayList<Country> getDestinations(@RequestParam String userName){
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("Caribbean",32.4));
        countries.add(new Country("Australia",35.1));
        for(Registry registry:registration){
            if(registry.userName().equals(userName)){
                return countries;
            }
        }
        return new ArrayList<Country>();
    }
}
