package ba.delalich.tablanette.controllers;

import ba.delalich.tablanette.repositories.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    private GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/games")
    public String getGames(Model model) {
        model.addAttribute("games", gameRepository.findAll());
        return "games";
    }
}
