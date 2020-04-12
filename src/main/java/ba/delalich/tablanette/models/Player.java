package ba.delalich.tablanette.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer playerId;

    private Integer indexx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @OneToMany(mappedBy = "player")
    private Set<PlayerDealResult> dealResult = new HashSet<>();

    @OneToMany(mappedBy = "player")
    private Set<PlayerTablanetteResult> tablanetteResult = new HashSet<>();

    public Player(int indexx, User user) {
        this.indexx = indexx;
        this.user = user;
    }
}
