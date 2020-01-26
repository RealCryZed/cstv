package cstv.Interfaces;

import cstv.Models.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<Match, Long> {

}
