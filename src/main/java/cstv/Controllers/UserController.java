package cstv.Controllers;

import cstv.Models.User;
import cstv.Services.MatchService;
import cstv.Services.PlayerService;
import cstv.Services.TeamService;
import cstv.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    /**
     * ModelAndView for login page
     * @param modelAndView gets login.html page
     * @return modelAndView
     */
    @RequestMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");

        return modelAndView;
    }

    /**
     * ModelAndView for registration page
     * @param modelAndView gets registration.html page
     * @return modelAndView
     */
    @GetMapping("/register")
    public ModelAndView getRegisterPage(ModelAndView modelAndView) {
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    /**
     *
     * @param modelAndView
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    public ModelAndView createNewUser(ModelAndView modelAndView,
                                      @Valid User user, BindingResult bindingResult) {
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult.rejectValue("username", "Username was already registered");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }

        return modelAndView;
    }

    /**
     * ModelAndView for profile page.
     * Gets the credentials from {@link Authentication} and finds user with given name in database.
     * @param modelAndView gets my-profile.html page
     * @return modelAndView
     */
    @GetMapping("/my-profile")
    public ModelAndView getMyProfilePage(ModelAndView modelAndView) {
        modelAndView.setViewName("my-profile");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

        modelAndView.addObject("user", user);
        modelAndView.addObject("player", playerService.findAllPlayers());
        modelAndView.addObject("teams", teamService.getTenFirstTeams());
        modelAndView.addObject("matches", matchService.getFiveLastMatchesNotEnded());
        modelAndView.addObject("endedMatches", matchService.getFiveLastEndedMatches());

        return modelAndView;
    }
}
