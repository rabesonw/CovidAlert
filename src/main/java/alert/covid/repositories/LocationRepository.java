package alert.covid.repositories;

import alert.covid.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface LocationRepository
 * Handles the link between the app and the database for the table locations with primary key of type long
 */
public interface LocationRepository extends JpaRepository<Location,Long> {
}
