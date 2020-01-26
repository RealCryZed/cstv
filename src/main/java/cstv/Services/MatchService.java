package cstv.Services;

import cstv.Interfaces.MatchRepository;
import cstv.Models.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepo;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    public Match addMatch(Match match) {
        match.setId(seqGenerator.generateSequence(Match.SEQUENCE_NAME));
        return matchRepo.save(match);
    }
}
