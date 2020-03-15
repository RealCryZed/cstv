package cstv.Services;

import cstv.Interfaces.EndedMatchRepository;
import cstv.Interfaces.MatchRepository;
import cstv.Models.EndedMatch;
import cstv.Models.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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

    /*
        Upcoming Matches
    */

    /**
     * Gets all match parameters from call and saves it to database.
     * @param match element of Match class, which takes all parameters
     * @return
     */
    public Match addMatch(Match match) {
        return matchRepo.save(match);
    }

    /**
     * Finds the match with specific id from database.
     * @param id unique value which related to every match
     * @return the match with specific id
     */
    public Match findMatchById(Integer id) {
        return matchRepo.findMatchById(id);
    }

    /**
     * Finds 5 closest upcoming matches with the specific team.
     * Saves list of matches in Cache.
     * @param teamName the name of a team
     * @return list of upcoming matches where this team will be involved
     */
    @Cacheable(value = "five-upc-matches-by-team")
    public List<Match> getFiveLastUpcomingMatchesByTeam(String teamName) {
        return matchRepo.findTop5ByFirstTeamNameOrSecondTeamNameIgnoreCase(teamName, teamName);
    }

    /**
     * Finds all upcoming matches in database.
     * Saves list of matches in Cache.
     * @return list of matches
     */
    @Cacheable(value = "all-upc-matches")
    public List<Match> getAllMatchesNotEnded() {
        return matchRepo.findTop50ByOrderById();
    }

    /**
     * Finds all upcoming matches for admin page in database.
     * Saves list of matches in Cache.
     * @return list of matches
     */
    public List<Match> getAllMatchesNotEnded_Admin() {
        return matchRepo.findTop50ByOrderById();
    }

    /**
     * Finds 5 closest upcoming matches in database.
     * Saves list of matches in Cache.
     * @return list of matches
     */
    @Cacheable(value = "five-upc-matches")
    public List<Match> getFiveLastMatchesNotEnded() {
        return matchRepo.findTop5ByOrderById();
    }

    /*
        Ended Matches
    */

    /**
     * Finds the ended match with specific id from database.
     * @param id unique value which related to every match
     * @return the ended match with specific id
     */
    public EndedMatch findEndedMatchById(Integer id) {
        return endedMatchRepo.findMatchById(id);
    }

    /**
     * Finds last 5 ended matches with the specific team.
     * Saves list of matches in Cache.
     * @param teamName the name of a team
     * @return list of ended matches where this team was involved
     */
    @Cacheable(value = "ten-ended-matches-by-team")
    public List<EndedMatch> get10LastEndedMatchesByTeam(String teamName) {

        return endedMatchRepo.findTop10ByFirstTeamNameOrSecondTeamNameIgnoreCaseOrderByIdEndedDesc(teamName, teamName);
    }

    /**
     * Finds last 5 ended matches in database.
     * Saves list of matches in Cache.
     * @return list of matches
     */
    @Cacheable(value = "five-ended-matches")
    public List<EndedMatch> getFiveLastEndedMatches() {
        return endedMatchRepo.findTop5ByOrderByIdEndedDesc();
    }

    /**
     * Finds last 30 ended matches in database.
     * Saves list of matches in Cache.
     * @return list of matches
     */
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
    }

    /**
     * Finds last 50 ended matches in database for the admin page.
     * @return list of matches
     */
    public List<EndedMatch> getAllEndedMatches_Admin() {
        return endedMatchRepo.findTop50ByOrderByIdEndedDesc();
    }

    /**
     * Ends match with score given by user.
     * Sets the state of a team according to the score team has.
     * Saves ended match in its database and deletes this match from upcoming matches database.
     * @param id value related to match
     * @param team1Score score of the first team given by user
     * @param team2Score score of the second team given by user
     * @param timeEnded the exact time when match was ended
     * @param dateEnded the exact date when match was ended
     */
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