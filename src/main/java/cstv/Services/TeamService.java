package cstv.Services;

import cstv.Interfaces.TeamRepository;
import cstv.Models.Player;
import cstv.Models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Team> findAllTeams_List() {
        return teamRepo.findAll();
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
        Set<String> playerList = new HashSet<>();
        playerList.add(team.getPlayer1());
        playerList.add(team.getPlayer2());
        playerList.add(team.getPlayer3());
        playerList.add(team.getPlayer4());
        playerList.add(team.getPlayer5());

        team.setId(seqGenerator.generateSequence(Team.SEQUENCE_NAME));
        team.setPlayers(playerList);
        teamRepo.save(team);
    }
}
