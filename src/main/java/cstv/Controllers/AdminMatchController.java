package cstv.Controllers;

import cstv.Models.EndedMatch;
import cstv.Models.Match;
import cstv.Services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminMatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/add-match")
    public ModelAndView getAddMatchPage(ModelAndView modelAndView) {
        modelAndView.addObject("match", new Match());
        modelAndView.setViewName("admin/add-match");

        return modelAndView;
    }

    @PostMapping("/add-match")
    public ModelAndView addMatch(ModelAndView modelAndView,
                                 @Valid Match match, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/add-match");
        } else {
            matchService.addMatch(match);
            modelAndView.addObject("successMessage", "Match has been created successfully");
            modelAndView.addObject("match", new Match());
            modelAndView.setViewName("admin/admin");

        }

        return modelAndView;
    }

    @GetMapping("/end-match")
    public ModelAndView getEndMatchPage(ModelAndView modelAndView) {
        modelAndView.addObject("matches", matchService.getAllMatchesNotEnded());
        modelAndView.addObject("endedMatches", matchService.getAllEndedMatches());
        modelAndView.setViewName("admin/end-match");

        return modelAndView;
    }

    @GetMapping("/end-match/{id}")
    public ModelAndView getEndMatch(ModelAndView modelAndView,
                                 @PathVariable Long id) {
        Match match = matchService.findMatchById(id);

        modelAndView.addObject("matchToEnd", match);

        modelAndView.setViewName("admin/endMatchById");
        return modelAndView;
    }

    @PostMapping("/end-match/{id}")
    public ModelAndView endMatch(ModelAndView modelAndView,
                                 @PathVariable Long id,
                                 @Valid @ModelAttribute("matchToEnd") Match matchToEnd,
                                 BindingResult bindingResult) {
        Match match = matchService.findMatchById(matchToEnd.getId());

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("matchToEnd", match);

            modelAndView.setViewName("redirect:/admin/end-match/error/" + id);
        } else {
            matchService.endMatchById(id, matchToEnd.getFirstTeamScore(), matchToEnd.getSecondTeamScore());
            modelAndView.setViewName("redirect:/admin/end-match");
        }

        return modelAndView;
    }
}