package ba.delalich.tablanette.services;

import ba.delalich.tablanette.models.Bull;
import ba.delalich.tablanette.models.Game;
import org.springframework.stereotype.Service;

@Service
public class BullService {

    public void createBull(Game game) {
        game.getBulls().add(new Bull());
    }
}
