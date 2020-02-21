package cstv.Interfaces;

import cstv.Models.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {

    Match findMatchById(Integer id);

    @Transactional
    void deleteMatchById(Integer id);

    List<Match> findTop5ByOrderById();

    List<Match> findTop50ByOrderById();

    List<Match> findTop5ByFirstTeamNameOrSecondTeamNameIgnoreCase(String firstTeamName, String secondTeamName);
}
