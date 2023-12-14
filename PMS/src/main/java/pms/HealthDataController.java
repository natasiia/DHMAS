package pms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/healthdata")
public class HealthDataController {
    @Autowired
    private HealthDataService healthDataService;

    @PostMapping
    public HealthData addHealthData(@RequestBody HealthData healthData) {
        return healthDataService.saveHealthData(healthData);
    }

    @GetMapping
    public List<HealthData> getAllHealthData() {
        return healthDataService.getAllHealthData();
    }

    @GetMapping("/{id}")
    public HealthData getHealthDataById(@PathVariable String id) {
        return healthDataService.getHealthDataById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteHealthData(@PathVariable String id) {
        healthDataService.deleteHealthData(id);
    }

}
