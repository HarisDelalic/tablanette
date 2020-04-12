package ba.delalich.tablanette.services;

import ba.delalich.tablanette.models.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BullServiceTest {

    @InjectMocks
    BullService bullService;

    @Test
    public void testCreateNewBull() {
        Game game = new Game(1, "game", new Date());

        this.bullService.createBull(game);

        assertThat(game.getBulls().size()).isEqualTo(1);
    }

}