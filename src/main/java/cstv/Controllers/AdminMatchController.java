package cstv.Controllers;

import cstv.Models.EndedMatch;
import cstv.Models.Guide;
import cstv.Models.Match;
import cstv.Services.GuideService;
import cstv.Services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminMatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private GuideService guideService;

    @GetMapping("/add-guide")
    public ModelAndView getAddGuidePage(ModelAndView modelAndView) {
        modelAndView.addObject("guide", new Guide());
        modelAndView.setViewName("admin/add-guide");

        return modelAndView;
    }

    @PostMapping("/add-guide")
    public ModelAndView addGuide(ModelAndView modelAndView,
                                 @Valid Guide guide,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/add-guide");
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter dateTimeFormatterOnlyDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String timeAndDatePublished = LocalDateTime.now().format(dateTimeFormatter);
            String datePublished = LocalDateTime.now().format(dateTimeFormatterOnlyDate);

            guideService.addGuide(guide, auth.getName(), timeAndDatePublished, datePublished);
            modelAndView.addObject("successMessage", "Guide has been created successfully");
            modelAndView.addObject("guide", new Guide());
            modelAndView.setViewName("admin/admin");
        }

        return modelAndView;
    }

    @GetMapping("/add-match")
    public ModelAndView getAddMatchPage(ModelAndView modelAndView) {
        modelAndView.addObject("match", new Match());
        modelAndView.setViewName("admin/add-match");

        return modelAndView;
    }

    @PostMapping("/add-match")
    public ModelAndView addMatch(ModelAndView modelAndView,
                                 @Valid Match match,
                                 BindingResult bindingResult) {
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
