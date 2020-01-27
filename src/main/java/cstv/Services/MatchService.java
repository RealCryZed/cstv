package cstv.Services;

import cstv.Interfaces.MatchRepository;
import cstv.Models.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Match> getAllMatches() {
        return matchRepo.findAll();
    }

    public Page<Match> getFiveLastMatches() {
        PageRequest page = PageRequest.of(
                0, 5, Sort.by("_id").ascending());
        return matchRepo.findAll(page);
    }
}
