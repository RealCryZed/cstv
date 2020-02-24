package cstv.Services;

import cstv.Configs.CachingConfig;
import cstv.Interfaces.PlayerRepository;
import cstv.Models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheManager="cacheManager1")
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepo;

    public Player findPlayerById(Integer id) {
        return playerRepo.findPlayerById(id);
    }

    @Cacheable(value = "all-players")
    public List<Player> findAllPlayers() {
        return playerRepo.findTop30ByOrderByPlace();
    }

    @Cacheable(value = "five-first-players")
    public List<Player> getFiveFirstPlayers() {
        return playerRepo.findTop5ByOrderByPlace();
    }

    public void savePlayer(Player player) {
        playerRepo.save(player);
    }
}
