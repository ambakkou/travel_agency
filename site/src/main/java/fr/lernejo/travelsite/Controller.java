package fr.lernejo.travelsite;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {
    final ServiceApi serviceApi;
    public Controller(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    @PostMapping(value = "/api/inscription")
    public ResponseEntity<HttpStatus> register(@RequestBody Registry registry){
        this.serviceApi.register(registry);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/api/travels")
    @ResponseBody
    public Object getDestinations(@RequestParam String userName) {
        return serviceApi.getDestinations(userName);
    }
}
