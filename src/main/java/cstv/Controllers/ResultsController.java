package cstv.Controllers;

import cstv.Models.EndedMatch;
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
public class ResultsController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @GetMapping("/results")
    public ModelAndView getResultsPage(ModelAndView modelAndView) {
        modelAndView.setViewName("results");

        modelAndView.addObject("players", playerService.getFiveFirstPlayers());
        modelAndView.addObject("teams", teamService.getFiveFirstTeams());
        modelAndView.addObject("matches", matchService.getAllMatchesNotEnded());
        modelAndView.addObject("endedMatches", matchService.getFiveLastEndedMatches());

        return modelAndView;
    }

    @GetMapping("/results/{id}")
    public ModelAndView getSingleEndedMatchPage(ModelAndView modelAndView,
                                           @PathVariable Long id) {
        try {
            EndedMatch endedMatch = matchService.findEndedMatchById(id);

            modelAndView.setViewName("single-endedMatch");

            modelAndView.addObject("singleEndedMatch", endedMatch);
            modelAndView.addObject("players", playerService.getFiveFirstPlayers());
            modelAndView.addObject("teams", teamService.getFiveFirstTeams());
            modelAndView.addObject("matches", matchService.getAllMatchesNotEnded());
            modelAndView.addObject("endedMatches", matchService.getFiveLastEndedMatches());
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/results");
            return modelAndView;
        }

        return modelAndView;
    }
}
