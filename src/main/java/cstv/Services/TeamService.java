package cstv.Services;

import cstv.Interfaces.TeamRepository;
import cstv.Models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    public Team findTeamByName(String nickname) {
        return teamRepo.findByNameIgnoreCase(nickname);
    }

    public Page<Team> findAllTeams() {
        PageRequest page = PageRequest.of(
                0, 40, Sort.by("place").ascending());
        return teamRepo.findAll(page);
    }

    public Page<Team> getFiveFirstTeams() {
        PageRequest page = PageRequest.of(
                0, 5, Sort.by("place").ascending());
        return teamRepo.findAll(page);
    }

    public Page<Team> getTenFirstTeams() {
        PageRequest page = PageRequest.of(
                0, 10, Sort.by("place").ascending());
        return teamRepo.findAll(page);
    }

    public void addTeam(Team team) {
        team.setId(seqGenerator.generateSequence(Team.SEQUENCE_NAME));
        teamRepo.save(team);
    }
}
