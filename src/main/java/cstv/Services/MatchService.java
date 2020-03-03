package cstv.Services;

import cstv.Interfaces.EndedMatchRepository;
import cstv.Interfaces.MatchRepository;
import cstv.Models.EndedMatch;
import cstv.Models.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@CacheConfig(cacheManager="cacheManager2")
public class MatchService {

    @Autowired
    private MatchRepository matchRepo;

    @Autowired
    private EndedMatchRepository endedMatchRepo;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    /*
        Upcoming Matches
    */

    public Match addMatch(Match match) {
        return matchRepo.save(match);
    }

    public Match findMatchById(Integer id) {
        return matchRepo.findMatchById(id);
    }

    @Cacheable(value = "five-upc-matches-by-team")
    public List<Match> getFiveLastUpcomingMatchesByTeam(String teamName) {
        return matchRepo.findTop5ByFirstTeamNameOrSecondTeamNameIgnoreCase(teamName, teamName);
    }

    @Cacheable(value = "all-upc-matches")
    public List<Match> getAllMatchesNotEnded() {
        return matchRepo.findTop50ByOrderById();
    }

    public List<Match> getAllMatchesNotEnded_Admin() {
        return matchRepo.findTop50ByOrderById();
    }

    @Cacheable(value = "five-upc-matches")
    public List<Match> getFiveLastMatchesNotEnded() {
        return matchRepo.findTop5ByOrderById();
    }

    /*
        Ended Matches
    */

    public EndedMatch findEndedMatchById(Integer id) {
        return endedMatchRepo.findMatchById(id);
    }

    @Cacheable(value = "five-ended-matches-by-team")
    public List<EndedMatch> getFiveLastEndedMatchesByTeam(String teamName) {

        return endedMatchRepo.findTop30ByFirstTeamNameOrSecondTeamNameIgnoreCaseOrderByIdEndedDesc(teamName, teamName);
    }

    @Cacheable(value = "five-ended-matches")
    public List<EndedMatch> getFiveLastEndedMatches() {
        return endedMatchRepo.findTop5ByOrderByIdEndedDesc();
    }

    @Cacheable(value = "thirty-ended-matches")
    public List<EndedMatch> get30LastEndedMatches() {
        List<String[]> endedMatchArrayList = endedMatchRepo.findTop30ByOrderByIdEndedDesc();

        List<EndedMatch> endedMatches = new LinkedList<>();

        for (String[] endedMatchSingle : endedMatchArrayList) {
            EndedMatch endedMatch = new EndedMatch();

            endedMatch.setId(Integer.valueOf(endedMatchSingle[0]));
            endedMatch.setFirstTeamName(endedMatchSingle[1]);
            endedMatch.setFirstTeamScore(Integer.valueOf(endedMatchSingle[2]));
            endedMatch.setFirstTeamState(endedMatchSingle[3]);
            endedMatch.setSecondTeamName(endedMatchSingle[4]);
            endedMatch.setSecondTeamScore(Integer.valueOf(endedMatchSingle[5]));
            endedMatch.setSecondTeamState(endedMatchSingle[6]);
            endedMatch.setTournament(endedMatchSingle[7]);

            endedMatches.add(endedMatch);
        }

        return endedMatches;
//        return endedMatchRepo.findTop30ByOrderByIdEndedDesc();
    }

    @Cacheable(value = "all-ended-matches")
    public List<EndedMatch> getAllEndedMatches() {
        return endedMatchRepo.findTop50ByOrderByIdEndedDesc();
    }

    public List<EndedMatch> getAllEndedMatches_Admin() {
        return endedMatchRepo.findTop50ByOrderByIdEndedDesc();
    }

    public void endMatchById(Integer id, Integer team1Score, Integer team2Score, String timeEnded, String dateEnded) {
        Match match = matchRepo.findMatchById(id);
        EndedMatch endedMatch = new EndedMatch();

        endedMatch.setId(match.getId());
        endedMatch.setEndedTime(timeEnded);
        endedMatch.setEndedDate(dateEnded);

        endedMatch.setFirstTeamName(match.getFirstTeamName());
        endedMatch.setFirstTeamScore(team1Score);

        endedMatch.setSecondTeamName(match.getSecondTeamName());
        endedMatch.setSecondTeamScore(team2Score);

        endedMatch.setTournament(match.getTournament());

        if (team1Score == 16 && team2Score <= 14 || team1Score == 19 && team2Score >= 15 && team2Score <= 17) {
            endedMatch.setFirstTeamState("win");
            endedMatch.setSecondTeamState("loss");
        } else if (team2Score == 16 && team1Score <= 14 || team2Score == 19 && team1Score >= 15 && team1Score <= 17){
            endedMatch.setFirstTeamState("loss");
            endedMatch.setSecondTeamState("win");
        }

        endedMatchRepo.save(endedMatch);
        matchRepo.deleteMatchById(id);
    }
}