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

import java.util.*;

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

    /**
     * Finds guide by given id in database.
     * Splits the paragraphs for better reading experience.
     * @param modelAndView gets single-guide.html page
     * @param id unique integer value which belongs to particular guide
     * @return modelAndView
     */
    @GetMapping("/guides/{id}")
    public ModelAndView getGuidePage(ModelAndView modelAndView,
                                     @PathVariable Integer id) {
        try {
            Guide guide = guideService.findGuideById(id);
            List<String> guideText = new LinkedList<>();

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
