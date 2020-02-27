package cstv.Controllers;

import cstv.Services.GuideService;
import cstv.Services.MatchService;
import cstv.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private GuideService guideService;

    @RequestMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("home");

        modelAndView.addObject("teams", teamService.getTenFirstTeams());
        modelAndView.addObject("matches", matchService.getFiveLastMatchesNotEnded());
        modelAndView.addObject("endedMatches", matchService.getFiveLastEndedMatches());
        modelAndView.addObject("guides", guideService.getLast15GuidesInformation());

        return modelAndView;
    }
}
