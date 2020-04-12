package ba.delalich.tablanette.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "player_deal_result")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDealResult {

    @EmbeddedId
    private PlayerDealResultKey id;

    @ManyToOne
    @MapsId("bull_id")
    @JoinColumn(name = "bull_id")
    private Bull bull;

    @ManyToOne
    @MapsId("player_id")
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "result")
    private Integer result;
}
