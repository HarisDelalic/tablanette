package ba.delalich.tablanette.controllers;

import ba.delalich.tablanette.models.Game;
import ba.delalich.tablanette.services.GameService;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    public void testRetrieveAllGames() throws Exception {
        List<Game> existingGames = Stream.of(
                new Game(1, "game1", new Date()),
                new Game(2, "game2", new Date()))
                .collect(Collectors.toList());

        given(gameService.findAll())
                .willReturn(existingGames);

        MvcResult result = mockMvc.perform(get("/games")).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getModelAndView().getModel().get("games")).isNotNull();
        assertThat(result.getModelAndView().getModel().get("games")).isEqualTo(existingGames);
    }

    @Test
    public void testCreateNewGame() throws Exception {
        Game game = new Game(0, "to_be_created", new Date());

        when(gameService.save(any(Game.class))).thenReturn(game);

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(
                        Arrays.asList(
                                new BasicNameValuePair("name", game.getName())
                                ))))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

}