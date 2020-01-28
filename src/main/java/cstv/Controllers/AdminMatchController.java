package cstv.Controllers;

import cstv.Models.Match;
import cstv.Services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
}
