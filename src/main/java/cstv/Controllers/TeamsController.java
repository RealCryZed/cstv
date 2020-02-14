package cstv.Controllers;

import cstv.Models.Team;
import cstv.Services.MatchService;
import cstv.Services.PlayerService;
import cstv.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "*")
public class TeamsController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchService matchService;

    @GetMapping("/teams/{name}")
    public ModelAndView getSingleTeamPage(ModelAndView modelAndView,
                                          @PathVariable String name) {
        modelAndView.setViewName("single-team");

        Team team = teamService.findTeamByName(name);

        modelAndView.addObject("team", team);
        modelAndView.addObject("teamsEndedMatches", matchService.getFiveLastEndedMatchesByTeam(team.getName()));
        modelAndView.addObject("players", playerService.getFiveFirstPlayers());
        modelAndView.addObject("teams", teamService.getFiveFirstTeams());
        modelAndView.addObject("matches", matchService.getAllMatchesNotEnded());
        modelAndView.addObject("endedMatches", matchService.getFiveLastEndedMatches());

        return modelAndView;
    }
}
