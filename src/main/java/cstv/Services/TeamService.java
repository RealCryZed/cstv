package cstv.Services;

import cstv.Interfaces.TeamRepository;
import cstv.Models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepo;

    public Team findTeamByName(String nickname) {
        return teamRepo.findByName(nickname);
    }

    public List<Team> findAllTeams() {
        return teamRepo.findAll();
    }

//    public void saveTeam(Integer id, String name, Integer place) {
//        Team team = new Team();
//        team.setId(id);
//        team.setName(name);
//        team.setPlace(place);
//        teamRepo.save(team);
//    }
}