package alert.covid.repositories;

import alert.covid.models.Location;
import alert.covid.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,String> {
}
