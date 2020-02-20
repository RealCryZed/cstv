package cstv.Interfaces;

import cstv.Models.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Player findByNickname(String nickname);

    Player findPlayerById(Integer id);

    List<Player> findAll();

    Page<Player> findAll(Pageable pageable);
}
