package cstv.Interfaces;

import cstv.Models.Player;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testPlayerRepository_whenSaveAndRetrieve_thenOK() {
        Player player = new Player();

        player.setTeam("Valve Team");
        player.setPlace(999);
        player.setKd("0.1");
        player.setNickname("Gabe Newell");

        playerRepository.save(player);

        Player foundPlayer = playerRepository.findPlayerById(player.getId());

        assertNotNull(foundPlayer);
        assertEquals(player.getNickname(), foundPlayer.getNickname());

        playerRepository.deleteById(player.getId());
    }

    @Test
    public void testPlayerRepository_whenFindAll_thenOK() {
        List<Player> players = playerRepository.findAll();
        assertThat(players.size(), is(greaterThanOrEqualTo(0)));
    }
}