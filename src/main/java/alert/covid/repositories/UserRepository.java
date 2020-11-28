package alert.covid.repositories;

import alert.covid.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface UserRepository
 * Handles the link between the app and the database for the table users with primary key of type long
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * Checks if user exists with its username (instead of its ID)
     * @param username String username of the user to check if exists
     * @return true if user exists, false if not
     */
    boolean existsUserByUsername(String username);

    /**
     * Fetches user with its username (instead of its ID)
     * @param username String username of the user to fetch
     * @return User fetched
     */
    User findByUsername(String username);

}
