package cstv.Services;

import cstv.Interfaces.PlayerRepository;
import cstv.Models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepo;

    public Player findPlayerByNickname(String nickname) {
        return playerRepo.findByNickname(nickname);
    }

    public List<Player> findAllPlayers() {
        return playerRepo.findAll();
    }

//    public void savePlayer(Long id, String username, Integer place) {
//        Player player = new Player();
//        player.setId(id);
//        player.setNickname(username);
//        player.setPlace(place);
//        playerRepo.save(player);
//    }
}
