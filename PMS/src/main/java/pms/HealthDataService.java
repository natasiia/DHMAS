package pms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HealthDataService {
    @Autowired
    private HealthDataRepository healthDataRepository;

    public HealthData saveHealthData(HealthData healthData) {
        return healthDataRepository.save(healthData);
    }

    public List<HealthData> getAllHealthData() {
        return healthDataRepository.findAll();
    }

public HealthData getHealthDataById(String id) {
    return healthDataRepository.findById(id).orElse(null);
}


    public void deleteHealthData(String id) {
        healthDataRepository.deleteById(id);
    }

}
