package cstv.Interfaces;

import cstv.Models.EndedMatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EndedMatchRepository extends JpaRepository<EndedMatch, Integer> {

    EndedMatch findMatchById(Integer id);

    List<EndedMatch> findTop5ByOrderByIdEndedDesc();

    /*
        Query doesn't work so far
    */

    @Query("select m.id, m.firstTeamName, m.firstTeamScore, m.firstTeamState," +
            "m.secondTeamName, m.secondTeamScore, m.secondTeamState, m.tournament" +
            " from EndedMatch m order by m.idEnded desc")
    List<String[]> findTop30ByOrderByIdEndedDesc();
//    List<EndedMatch> findTop30ByOrderByIdEndedDesc();

    List<EndedMatch> findTop50ByOrderByIdEndedDesc();

    List<EndedMatch> findTop30ByFirstTeamNameOrSecondTeamNameIgnoreCaseOrderByIdEndedDesc(String firstTeamName, String secondTeamName);
}
