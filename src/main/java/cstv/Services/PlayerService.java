package cstv.Services;

import cstv.Interfaces.PlayerRepository;
import cstv.Models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Player findPlayerById(Integer id) {
        return playerRepo.findPlayerById(id);
    }

    public Page<Player> getFiveFirstPlayers() {
        PageRequest page = PageRequest.of(
                0, 5, Sort.by("_id").ascending());
        return playerRepo.findAll(page);
    }

    public void savePlayer(Player player) {
        playerRepo.save(player);
    }
}
