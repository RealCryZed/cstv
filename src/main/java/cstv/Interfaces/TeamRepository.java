package cstv.Interfaces;

import cstv.Models.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team, Integer> {

    Team findByNameIgnoreCase(String name);

    List<Team> findAll();

    Page<Team> findAll(Pageable pageable);
}
