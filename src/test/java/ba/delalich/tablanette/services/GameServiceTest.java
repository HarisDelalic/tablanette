package ba.delalich.tablanette.services;

import ba.delalich.tablanette.models.Game;
import ba.delalich.tablanette.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private UserService userService;

    @Test
    public void onGameCreate_defaultPlayersAdded() {
        Game game = new Game(0, "game", new Date());
        List<String> defaultUserNames = Stream.of(User.DELA, User.MAJI, User.AMA).collect(Collectors.toList());
        List<User> users = new ArrayList<>(
                Arrays.asList(
                        new User(0, User.DELA, "user", new Date(), new Date()),
                        new User(1, User.MAJI, "user", new Date(), new Date()),
                        new User(2, User.AMA, "user", new Date(), new Date())
                )
        );

        Mockito.when(userService.getUsersByName(defaultUserNames))
                .thenReturn(users);

        gameService.addDefaultPlayers(game);

        assertThat(game.getPlayers().size()).isEqualTo(3);
        assertThat(game.getPlayers().contains(users.get(0)));
        assertThat(game.getPlayers().contains(users.get(1)));
        assertThat(game.getPlayers().contains(users.get(2)));

    }
}