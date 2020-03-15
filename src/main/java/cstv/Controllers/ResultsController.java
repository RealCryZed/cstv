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

    /**
     * Finds all ended matches and shows it on page.
     * @param modelAndView gets results.html page
     * @return modelAndView
     */
    @GetMapping("/results")
    public ModelAndView getResultsPage(ModelAndView modelAndView) {
        modelAndView.setViewName("results");

        modelAndView.addObject("players", playerService.getFiveFirstPlayers());
        modelAndView.addObject("teams", teamService.getFiveFirstTeams());
        modelAndView.addObject("matches", matchService.getAllMatchesNotEnded());
        modelAndView.addObject("endedMatches", matchService.get30LastEndedMatches());

        return modelAndView;
    }

    /**
     * Finds endedMatch by given id in database and shows it on page.
     * @param modelAndView gets single-endedMatch.html page
     * @param id unique integer value which belongs to particular endedMatch
     * @return modelAndView
     */
    @GetMapping("/results/{id}")
    public ModelAndView getSingleEndedMatchPage(ModelAndView modelAndView,
                                           @PathVariable Integer id) {
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
