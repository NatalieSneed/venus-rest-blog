package sneed.venusrestblog.data.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import sneed.venusrestblog.data.User;


public interface UsersRepository extends JpaRepository<User, Long>{
}
