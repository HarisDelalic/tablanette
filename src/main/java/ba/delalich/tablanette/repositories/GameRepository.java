package ba.delalich.tablanette.repositories;

import ba.delalich.tablanette.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> { }
