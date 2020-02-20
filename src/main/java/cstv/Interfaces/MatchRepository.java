package cstv.Interfaces;

import cstv.Models.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {

    Page<Match> findAll(Pageable pageable);

    List<Match> findAllByEnded(Integer ended);

    Page<Match> findAllByEnded(Integer ended, Pageable pageable);

    Page<Match> findAllByFirstTeamNameOrSecondTeamNameIgnoreCase(Pageable page, String firstTeamName, String secondTeamName);

    Match findMatchById(Integer id);

    @Transactional
    void deleteMatchById(Integer id);
}
