package pms;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthDataRepository extends JpaRepository<HealthData, String> {
    // Any additional methods, if necessary
}
