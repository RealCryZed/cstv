package cstv.Interfaces;

import cstv.Models.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team, Integer> {

    Team findByName(String name);

    List<Team> findAll();
}
