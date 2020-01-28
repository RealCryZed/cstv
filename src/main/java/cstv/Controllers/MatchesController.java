package cstv.Controllers;

import cstv.Services.MatchService;
import cstv.Services.PlayerService;
import cstv.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/matches")
    public ModelAndView getMatchesPage(ModelAndView modelAndView) {
        modelAndView.setViewName("matches");

        modelAndView.addObject("players", playerService.getFiveFirstPlayers());
        modelAndView.addObject("teams", teamService.getFiveFirstTeams());
        modelAndView.addObject("matches", matchService.getAllMatches());

        return modelAndView;
    }
}
