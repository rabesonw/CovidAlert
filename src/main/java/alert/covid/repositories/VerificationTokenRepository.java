package alert.covid.repositories;

import alert.covid.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface VerificationTokenRepository
 * Handles the link between the app and the database for the table verif_tokens with primary key of type long
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,String> {
}
