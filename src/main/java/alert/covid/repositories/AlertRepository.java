package alert.covid.repositories;

import alert.covid.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface AlertRepository
 * Handles the link between the app and the database for the table alerts with primary key of type long
 */
public interface AlertRepository extends JpaRepository<Alert,Long> {
}
