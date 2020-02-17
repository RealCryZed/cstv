package cstv.Interfaces;

import cstv.Models.EndedMatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EndedMatchRepository extends MongoRepository<EndedMatch, Long> {

    EndedMatch findMatchById(Long id);

    Page<EndedMatch> findAll(Pageable pageable);

    Page<EndedMatch> findAllByFirstTeamNameOrSecondTeamNameIgnoreCase(Pageable page, String firstTeamName, String secondTeamName);
}
