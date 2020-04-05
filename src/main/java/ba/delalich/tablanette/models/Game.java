package ba.delalich.tablanette.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int gameId;

    private String name;

    @Column(name="created_at")
    private Date createdAt;

    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER

    )
    private Set<Player> players;

    public Game(int gameId, String name, Date createdAt) {
        this.gameId = gameId;
        this.name = name;
        this.createdAt = createdAt;
    }
}
