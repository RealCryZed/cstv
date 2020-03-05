package cstv.Controllers;

import cstv.Models.EndedMatch;
import cstv.Models.Match;
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

import java.util.List;

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
        Team team = teamService.findTeamByName(name);

        List<Match> upcomingMatches = matchService.getFiveLastUpcomingMatchesByTeam(team.getName());
        List<EndedMatch> endedMatches = matchService.getFiveLastEndedMatchesByTeam(team.getName());

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

        modelAndView.addObject("team", team);
        modelAndView.addObject("teamPlayers", playerService.findAllPlayersByTeam(team.getName()));
        modelAndView.addObject("players", playerService.getFiveFirstPlayers());
        modelAndView.addObject("teams", teamService.getFiveFirstTeams());
        modelAndView.addObject("matches", matchService.getAllMatchesNotEnded());
        modelAndView.addObject("endedMatches", matchService.getFiveLastEndedMatches());

        modelAndView.setViewName("single-team");

        return modelAndView;
    }
}
