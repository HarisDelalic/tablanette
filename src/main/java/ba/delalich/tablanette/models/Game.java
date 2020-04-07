package ba.delalich.tablanette.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer gameId;

    private String name;

    @Column(name="created_at")
    private Date createdAt;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false)
    private Set<Player> players = new HashSet<Player>();

    public Game(int gameId, String name, Date createdAt) {
        this.gameId = gameId;
        this.name = name;
        this.createdAt = createdAt;
    }
}
