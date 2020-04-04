package ba.delalich.tablanette.controllers;

import ba.delalich.tablanette.models.Game;
import ba.delalich.tablanette.repositories.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameRepository gameRepository;

    @Test
    public void testRetrieveAllGames() throws Exception {
        List<Game> existingGames = Stream.of(
                new Game(1, "game1", new Date()),
                new Game(2, "game2", new Date()))
                .collect(Collectors.toList());

        given(gameRepository.findAll())
                .willReturn(existingGames);

        MvcResult result = mockMvc.perform(get("/games")).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getModelAndView().getModel().get("games")).isNotNull();
        assertThat(result.getModelAndView().getModel().get("games")).isEqualTo(existingGames);
    }
}