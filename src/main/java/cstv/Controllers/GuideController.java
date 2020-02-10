package cstv.Controllers;

import cstv.Models.Guide;
import cstv.Services.GuideService;
import cstv.Services.MatchService;
import cstv.Services.PlayerService;
import cstv.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

@Controller
@CrossOrigin(origins = "*")
public class GuideController {

    @Autowired
    private GuideService guideService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @GetMapping("/guides/{id}")
    public ModelAndView getGuidePage(ModelAndView modelAndView,
                                     @PathVariable Long id) {
        try {
            Guide guide = guideService.findGuideById(id);
            Set<String> guideText = new TreeSet<>();

            String[] arrSplit = guide.getText().split("\\r?\\n");

            guideText.addAll(Arrays.asList(arrSplit));

            modelAndView.addObject("singleGuide", guide);
            modelAndView.addObject("singleGuideText", guideText);
            modelAndView.addObject("players", playerService.getFiveFirstPlayers());
            modelAndView.addObject("teams", teamService.getFiveFirstTeams());
            modelAndView.addObject("matches", matchService.getAllMatchesNotEnded());
            modelAndView.addObject("endedMatches", matchService.getFiveLastEndedMatches());

            modelAndView.setViewName("single-guide");
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        return modelAndView;
    }
}
