package cstv.Services;

import cstv.Interfaces.PlayerRepository;
import cstv.Models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepo;

    public Player findPlayerByNickname(String nickname) {
        return playerRepo.findByNickname(nickname);
    }

    public Player findPlayerById(Integer id) {
        return playerRepo.findPlayerById(id);
    }

    public List<Player> findAllPlayers() {
        return playerRepo.findTop30ByOrderByPlace();
    }

    public List<Player> getFiveFirstPlayers() {
        return playerRepo.findTop5ByOrderByPlace();
    }

    public void savePlayer(Player player) {
        playerRepo.save(player);
    }
}
