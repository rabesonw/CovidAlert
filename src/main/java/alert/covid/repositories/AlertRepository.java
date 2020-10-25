package alert.covid.repositories;

import alert.covid.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert,Long> {
}
