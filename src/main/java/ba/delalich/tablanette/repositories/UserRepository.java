package ba.delalich.tablanette.repositories;

import ba.delalich.tablanette.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> getUsersByNameIn(List<String> names);
}
