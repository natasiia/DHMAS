package pms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/generalInfo")
public class HealthDataController {
    @Autowired
    private HealthDataService healthDataService;

    @PostMapping
    public ResponseEntity<Object> addHealthData(@RequestBody HealthData healthData) {

        HealthData res = healthDataService.saveHealthData(healthData);
        try {
            return ResponseEntity.created(new URI("/api/generalInfo/" + res.getPatientId())).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping
    public List<HealthData> getAllHealthData() {
        return healthDataService.getAllHealthData();
    }

    @GetMapping("/{id}")
    public HealthData getHealthDataById(@PathVariable Integer id) {
        return healthDataService.getHealthDataById(id.toString());
    }

    @DeleteMapping("/{id}")
    public void deleteHealthData(@PathVariable Integer id) {
        healthDataService.deleteHealthData(id.toString());
    }

}
