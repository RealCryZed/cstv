package cstv.Controllers;

import cstv.Services.MatchService;
import cstv.Services.PlayerService;
import cstv.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RankingController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @RequestMapping("/ranking")
    public ModelAndView getRankingPage(ModelAndView modelAndView) {
        modelAndView.setViewName("ranking");

        modelAndView.addObject("player", playerService.findAllPlayers());
        modelAndView.addObject("teams", teamService.findAllTeams());
        modelAndView.addObject("matches", matchService.getFiveLastMatchesNotEnded());

        return modelAndView;
    }
}
