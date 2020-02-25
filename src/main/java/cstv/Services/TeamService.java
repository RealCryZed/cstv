package cstv.Services;

import cstv.Configs.CachingConfig;
import cstv.Interfaces.TeamRepository;
import cstv.Models.Player;
import cstv.Models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@CacheConfig(cacheManager="cacheManager1")
public class TeamService {

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    public Team findTeamByName(String nickname) {
        return teamRepo.findByNameIgnoreCase(nickname);
    }

    @Cacheable(value ="five-first-teams")
    public List<Team> getFiveFirstTeams() {
        return teamRepo.findTop5ByOrderByPlace();
    }

    @Cacheable(value = "ten-first-teams")
    public List<Team> getTenFirstTeams() {

        return teamRepo.findTop10ByOrderByPlace();
    }

    @Cacheable(value = "all-teams")
    public List<Team> findAllTeams() {
        return teamRepo.findTop35ByOrderByPlace();
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
