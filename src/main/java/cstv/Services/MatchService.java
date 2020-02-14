package cstv.Services;

import cstv.Interfaces.EndedMatchRepository;
import cstv.Interfaces.MatchRepository;
import cstv.Models.EndedMatch;
import cstv.Models.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepo;

    @Autowired
    private EndedMatchRepository endedMatchRepo;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    public Match findMatchById(Long id) {
        return matchRepo.findMatchById(id);
    }

    public Page<EndedMatch> getFiveLastEndedMatchesByTeam(String teamName) {
        PageRequest page = PageRequest.of(
                0, 5, Sort.by("_id").ascending());
        return endedMatchRepo.findAllByFirstTeamNameOrSecondTeamNameIgnoreCase(page, teamName, teamName);
    }

    public Match addMatch(Match match) {
        match.setId(seqGenerator.generateSequence(Match.SEQUENCE_NAME));
        return matchRepo.save(match);
    }

    public List<EndedMatch> getAllEndedMatches() {
        return endedMatchRepo.findAll();
    }

    public Page<EndedMatch> getFiveLastEndedMatches() {
        PageRequest page = PageRequest.of(
                0, 5, Sort.by("_id").ascending());
        return endedMatchRepo.findAll(page);
    }

    public List<Match> getAllMatchesNotEnded() {
        return matchRepo.findAllByEnded(0);
    }

    public Page<EndedMatch> getEightLastEndedMatches() {
        PageRequest page = PageRequest.of(
                0, 8, Sort.by("_id").ascending());
        return endedMatchRepo.findAll(page);
    }

    public Page<Match> getFiveLastMatchesNotEnded() {
        PageRequest page = PageRequest.of(
                0, 5, Sort.by("_id").ascending());
        return matchRepo.findAllByEnded(0, page);
    }

    public void endMatchById(Long id, Integer team1Score, Integer team2Score, String timeEnded, String dateEnded) {
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
