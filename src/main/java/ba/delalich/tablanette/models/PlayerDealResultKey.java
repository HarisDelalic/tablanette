package ba.delalich.tablanette.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PlayerDealResultKey {

    @Column(name = "bull_id")
    private Integer bullId;

    @Column(name = "player_id")
    private Integer playerId;
}
