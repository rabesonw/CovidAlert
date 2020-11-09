package alert.covid.repositories;

import alert.covid.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsUserByUsername(String username);
    User findByUsername(String username);

}
