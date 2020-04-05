package ba.delalich.tablanette.util;

import ba.delalich.tablanette.models.Player;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionMapper {

    public static final String getNames(Stream<Player> stream) {
        return stream.map(player -> player.getUser().getName()).collect(Collectors.joining(", "));
    }
}
