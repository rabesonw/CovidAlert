package alert.covid.repositories;

import alert.covid.models.CovidTest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface CovidTestRepository
 * Handles the link between the app and the database for the table covid_tests with primary key of type long
 */
public interface CovidTestRepository extends JpaRepository<CovidTest,Long> {
}
