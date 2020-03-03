package cstv.Interfaces;

import cstv.Models.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team, Integer> {

    Team findByNameIgnoreCase(String name);

    List<Team> findTop5ByOrderByPlace();

    List<Team> findTop10ByOrderByPlace();

    List<Team> findTop35ByOrderByPlace();

    List<Team> findAllByOrderByName();
}
