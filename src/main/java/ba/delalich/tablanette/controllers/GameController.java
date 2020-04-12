package ba.delalich.tablanette.controllers;

import ba.delalich.tablanette.models.Game;
import ba.delalich.tablanette.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public String getGames(Model model) {
        model.addAttribute("games", gameService.findAll());
        model.addAttribute("game", new Game());
        return "games";
    }

    @PostMapping("/games")
    public String createGame(@ModelAttribute Game game) {
        this.gameService.addDefaultPlayers(game);
        this.gameService.createBull(game);
        this.gameService.save(game);

        return "redirect:/games/" + game.getGameId();
    }

    @GetMapping("/games/{id}")
    public String getGame(Model model, @PathVariable String id) {
        model.addAttribute("game", this.gameService.findById(id));
        return "game";
    }

}
