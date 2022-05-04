package cstv.Controllers;

import cstv.Models.Match;
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
public class MatchesController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    /**
     * Finds all matches from database.
     * @param modelAndView gets matches.html page
     * @return modelAndView
     */
    @GetMapping("/matches")
    public ModelAndView getMatchesPage(ModelAndView modelAndView) {
        modelAndView.setViewName("matches");

        modelAndView.addObject("players", playerService.getFiveFirstPlayers());
        modelAndView.addObject("teams", teamService.getFiveFirstTeams());
        modelAndView.addObject("matches", matchService.getAllMatchesNotEnded());
        modelAndView.addObject("endedMatches", matchService.getFiveLastEndedMatches());

        return modelAndView;
    }

    /**
     * Finds match by given id in database.
     * @param modelAndView gets single-match.html page
     * @param id unique integer value which belongs to particular match
     * @return modelAndView
     */
    @GetMapping("/matches/{id}")
    public ModelAndView getSingleMatchPage(ModelAndView modelAndView,
                                           @PathVariable Integer id) {
        try {
            Match match = matchService.findMatchById(id);

            modelAndView.setViewName("single-match");

            modelAndView.addObject("singleMatch", match);
            modelAndView.addObject("players", playerService.getFiveFirstPlayers());
            modelAndView.addObject("teams", teamService.getFiveFirstTeams());
            modelAndView.addObject("matches", matchService.getAllMatchesNotEnded());
            modelAndView.addObject("endedMatches", matchService.getFiveLastEndedMatches());
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/matches");
            return modelAndView;
        }

        return modelAndView;
    }
}
