package cstv.Interfaces;

import cstv.Models.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<Match, Long> {

    Page<Match> findAll(Pageable pageable);
}
