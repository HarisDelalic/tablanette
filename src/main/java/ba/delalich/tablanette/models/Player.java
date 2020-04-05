package ba.delalich.tablanette.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int playerId;

    @NotBlank
    private int index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player )) return false;
        return playerId != 0 && playerId == (((Player) o).getPlayerId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
