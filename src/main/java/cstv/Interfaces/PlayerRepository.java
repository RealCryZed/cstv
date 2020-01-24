package cstv.Interfaces;

import cstv.Models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, Integer> {

    Player findByNickname(String nickname);

    Player findPlayerById(Integer id);

    List<Player> findAll();
}
