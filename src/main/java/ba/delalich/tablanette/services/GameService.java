package ba.delalich.tablanette.services;

import ba.delalich.tablanette.models.Game;
import ba.delalich.tablanette.repositories.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findAll() {
        return this.gameRepository.findAll();
    }

    public Game findById(String id) {
        int game_id = Integer.parseInt(id);
        Optional<Game> game = this.gameRepository.findById(game_id);

        return getGameOrThrowException(game);
    }

    private Game getGameOrThrowException(Optional<Game> game) {
        if (game.isPresent()) {
            return game.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game Not Found");
        }
    }

    public Game save(Game game) {
        return this.gameRepository.save(game);
    }
}
