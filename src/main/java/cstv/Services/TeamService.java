package cstv.Services;

import cstv.Interfaces.TeamRepository;
import cstv.Models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@CacheConfig(cacheManager="cacheManager1")
public class TeamService {

    @Autowired
    private TeamRepository teamRepo;

    /**
     * Finds the team with specific name in database.
     * @param name value related to team
     * @return team which has been found in database
     */
    public Team findTeamByName(String name) {
        return teamRepo.findByNameIgnoreCase(name);
    }

    /**
     * Finds top 5 teams by rank in database.
     * Saves list of teams in Cache.
     * @return list of teams
     */
    @Cacheable(value ="five-first-teams")
    public List<Team> getFiveFirstTeams() {
        return teamRepo.findTop5ByOrderByPlace();
    }

    /**
     * Finds top 10 teams by rank in database.
     * Saves list of teams in Cache.
     * @return list of teams
     */
    @Cacheable(value = "ten-first-teams")
    public List<Team> getTenFirstTeams() {
        return teamRepo.findTop10ByOrderByPlace();
    }

    /**
     * Finds top 35 teams by rank in database.
     * Saves list of teams in Cache.
     * @return list of teams
     */
    @Cacheable(value = "all-teams-35")
    public List<Team> findAllTeams() {
        return teamRepo.findTop35ByOrderByPlace();
    }

    /**
     * Finds all teams by rank in database.
     * Saves list of teams in Cache.
     * @return list of teams
     */
    @Cacheable(value = "all-teams-sorted")
    public List<Team> findAllTeams_Sorted() {
        return teamRepo.findAllByOrderByName();
    }

    /**
     * Gets all team credentials from call.
     * Adds every player to the list.
     * Saves team in database.
     * @param team element of Team class with given credentials
     */
    public void addTeam(Team team) {
        Set<String> playerList = new HashSet<>();
        playerList.add(team.getPlayer1());
        playerList.add(team.getPlayer2());
        playerList.add(team.getPlayer3());
        playerList.add(team.getPlayer4());
        playerList.add(team.getPlayer5());

        team.setPlayers(playerList);
        teamRepo.save(team);
    }
}
