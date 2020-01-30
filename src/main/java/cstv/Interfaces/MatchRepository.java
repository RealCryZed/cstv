package cstv.Interfaces;

import cstv.Models.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MatchRepository extends MongoRepository<Match, Long> {

    Page<Match> findAll(Pageable pageable);

    List<Match> findAllByEnded(Integer ended);

    Page<Match> findAllByEnded(Integer ended, Pageable pageable);

    Match findMatchById(Long id);

    void deleteMatchById(Long id);
}
