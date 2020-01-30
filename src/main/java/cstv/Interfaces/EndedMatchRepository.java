package cstv.Interfaces;

import cstv.Models.EndedMatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EndedMatchRepository extends MongoRepository<EndedMatch, Long> {

    Page<EndedMatch> findAll(Pageable pageable);
}
