package cstv.Controllers;

import cstv.Models.EndedMatch;
import cstv.Models.Match;
import cstv.Models.Player;
import cstv.Services.MatchService;
import cstv.Services.PlayerService;
import cstv.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class PlayerController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchService matchService;

    /**
     * Finds player by given id in database.
     * Finds player's teammates by team in database.
     * Finds upcomingMatches and endedMatches from database by player's team name.
     * @param modelAndView gets single-player.html page
     * @param id unique integer value which belongs to particular player
     * @return modelAndView
     */
    @GetMapping("/players/{id}")
    public ModelAndView getSingleTeamPage(ModelAndView modelAndView,
                                          @PathVariable Integer id) {
        modelAndView.setViewName("single-player");

        Player player = playerService.findPlayerById(id);

        List<Player> teammates = playerService.findAllPlayersByTeam(player.getTeam());
        teammates.remove(player);

        List<Match> upcomingMatches = matchService.getFiveLastUpcomingMatchesByTeam(player.getTeam());
        List<EndedMatch> endedMatches = matchService.get10LastEndedMatchesByTeam(player.getTeam());

        if (upcomingMatches.size() == 0) {
            modelAndView.addObject("isUpcomingMatchesEmpty", true);
            modelAndView.addObject("noUpcomingMatchesYet", "No upcoming matches yet");
        } else {
            modelAndView.addObject("teamsUpcomingMatches", upcomingMatches);
        }

        if (endedMatches.size() == 0) {
            modelAndView.addObject("isEndedMatchesEmpty", true);
            modelAndView.addObject("noEndedMatchesYet", "The team didn't play a single match");
        } else {
            modelAndView.addObject("teamsEndedMatches", endedMatches);
        }

        modelAndView.addObject("player", player);
        modelAndView.addObject("teammates", teammates);
        modelAndView.addObject("players", playerService.getFiveFirstPlayers());
        modelAndView.addObject("teams", teamService.getFiveFirstTeams());
        modelAndView.addObject("matches", matchService.getAllMatchesNotEnded());
        modelAndView.addObject("endedMatches", matchService.getFiveLastEndedMatches());

        return modelAndView;
    }
}
