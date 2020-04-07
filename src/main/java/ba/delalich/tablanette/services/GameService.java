package ba.delalich.tablanette.services;

import ba.delalich.tablanette.models.Game;
import ba.delalich.tablanette.models.Player;
import ba.delalich.tablanette.models.User;
import ba.delalich.tablanette.repositories.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserService userService;

    public GameService(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
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

    public void addDefaultPlayers(Game game) {
        List<String> defaultUserNames = getDefaultUsernames();
        List<User> defaultUsers = getDefaultUsers(defaultUserNames);
        setGameDefaultPlayers(game, defaultUsers);
    }

    private List<String> getDefaultUsernames() {
        return Stream.of("dela","maji","ama").collect(Collectors.toList());
    }

    private List<User> getDefaultUsers(List<String> defaultUserNames) {
        return userService.getUsersByName(defaultUserNames);
    }

    private void setGameDefaultPlayers(Game game, List<User> defaultUsers) {
        Set<Player> defaultPlayers = IntStream
                .range(0, defaultUsers.size())
                .mapToObj(i -> new Player(i, defaultUsers.get(i)))
                .collect(Collectors.toSet());
        game.setPlayers(defaultPlayers);
    }
}
