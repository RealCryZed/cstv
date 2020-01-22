package cstv.Interfaces;

import cstv.Models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlayerRepository extends MongoRepository<Player, Long> {

    Player findByNickname(String nickname);

    List<Player> findAll();
}
