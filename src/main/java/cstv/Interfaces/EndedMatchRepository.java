package cstv.Interfaces;

import cstv.Models.EndedMatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndedMatchRepository extends JpaRepository<EndedMatch, Integer> {

    EndedMatch findMatchById(Integer id);

    Page<EndedMatch> findAll(Pageable pageable);

    Page<EndedMatch> findAllByFirstTeamNameOrSecondTeamNameIgnoreCase(Pageable page, String firstTeamName, String secondTeamName);
}
