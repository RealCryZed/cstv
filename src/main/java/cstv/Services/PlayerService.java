package cstv.Services;

import cstv.Interfaces.PlayerRepository;
import cstv.Models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheManager="cacheManager1")
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepo;

    /**
     * Finds the player with specific id in database.
     * @param id unique value related to player
     * @return player which has been found in database
     */
    public Player findPlayerById(Integer id) {
        return playerRepo.findPlayerById(id);
    }/**
     * Finds all players by team in database.
     * Saves list of players in Cache.
     * @return list of players
     */

    /**
     * Finds all players by team name in database.
     * Saves list of players in Cache.
     * @param team the name of a team the player plays for
     * @return list of players
     */
    @Cacheable(value = "all-players-by-team")
    public List<Player> findAllPlayersByTeam(String team) {
        return playerRepo.findPlayerByTeam(team);
    }

    /**
     * Finds all players in database.
     * Saves list of players in Cache.
     * @return list of players
     */
    @Cacheable(value = "all-players")
    public List<Player> findAllPlayers() {
        return playerRepo.findTop30ByOrderByPlace();
    }

    /**
     * Finds 5 first players by rank in database.
     * Saves list of players in Cache.
     * @return list of players
     */
    @Cacheable(value = "five-first-players")
    public List<Player> getFiveFirstPlayers() {
        return playerRepo.findTop5ByOrderByPlace();
    }

    /**
     * Saves player with given credentials in database.
     * @param player element of Player class
     */
    public void savePlayer(Player player) {
        playerRepo.save(player);
    }
}
