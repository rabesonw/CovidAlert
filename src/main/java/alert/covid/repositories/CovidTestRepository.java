package alert.covid.repositories;

import alert.covid.models.CovidTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidTestRepository extends JpaRepository<CovidTest,Long> {
}
