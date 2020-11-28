package alert.covid.repositories;

import alert.covid.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface AuthorityRepository
 * Handles the link between the app and the database for the table authority with primary key of type long
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
