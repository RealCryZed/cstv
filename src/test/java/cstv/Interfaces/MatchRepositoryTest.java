package cstv.Interfaces;

import cstv.Models.Match;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class MatchRepositoryTest {

    @Autowired
    private MatchRepository matchRepository;

    @Test
    public void testMatchRepository_whenSaveAndRetrieveEntity_thenOK() {
        Match match = new Match();
        match.setFirstTeamName("Furia");
        match.setSecondTeamName("Natus Vincere");
        match.setTimeOfStart("18:00");
        match.setTournament("ESL Pro League");

        matchRepository.save(match);
        Match foundMatch = matchRepository.findMatchById(match.getId());

        assertNotNull(foundMatch);
        assertEquals(match.getFirstTeamName(), foundMatch.getFirstTeamName());

        matchRepository.deleteMatchById(match.getId());
    }

    @Test
    public void testMatchesRepository_whenFindAll_thenOK() {
        List<Match> matches = matchRepository.findAll();
        assertThat(matches.size(), is(greaterThanOrEqualTo(0)));
    }
}