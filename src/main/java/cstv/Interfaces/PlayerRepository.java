package cstv.Interfaces;

import cstv.Models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Player findByNickname(String nickname);

    Player findByPlace(Integer place);

    Player findPlayerById(Integer id);

    List<Player> findTop5ByOrderByPlace();

    List<Player> findTop30ByOrderByPlace();
}
