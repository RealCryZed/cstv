package cstv.Controllers;

import cstv.Models.*;
import cstv.Services.GuideService;
import cstv.Services.MatchService;
import cstv.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminMatchController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private GuideService guideService;


    /**
     * Adds new Guide to the modelAndView entity for further guide creation.
     * @param modelAndView sets add-guide.html page
     * @return modelAndView
     */
    @GetMapping("/add-guide")
    public ModelAndView getAddGuidePage(ModelAndView modelAndView) {
        modelAndView.addObject("guide", new Guide());
        modelAndView.setViewName("admin/add-guide");

        return modelAndView;
    }

    /**
     * Adds new guide to the database if Guide entity is valid.
     * DateTimeFormatter formats the time and sets it to the guide entity.
     * @param modelAndView returns to the admin.html page
     * @param guide validated entity of Guide class
     * @param bindingResult entity for checking if page has errors while completing the task
     * @return modelAndView
     */

    @PostMapping("/add-guide")
    public ModelAndView addGuide(ModelAndView modelAndView,
                                 @Valid Guide guide,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/add-guide");
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
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

    /**
     * Adds new Team to the modelAndView entity for further team creation.
     * @param modelAndView gets add-team.html page
     * @return modelAndView
     */
    @GetMapping("/add-team")
    public ModelAndView getAddTeamPage(ModelAndView modelAndView) {
        modelAndView.addObject("team", new Team());
        modelAndView.setViewName("admin/add-team");

        return modelAndView;
    }

    /**
     * Adds new team to the database if Team entity is valid.
     * @param modelAndView returns to the admin.html page
     * @param team validated entity of Team class
     * @param bindingResult entity for checking if page has errors while completing the task
     * @return modelAndView
     */
    @PostMapping("/add-team")
    public ModelAndView addTeam(ModelAndView modelAndView,
                                @Valid Team team,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/add-team");
        } else {
            addTeamApi(team);
            modelAndView.addObject("successMessage", "Team has been added successfully");
            modelAndView.addObject("team", new Team());
            modelAndView.setViewName("admin/admin");

        }

        return modelAndView;
    }

    /**
     * Adds new Match to the modelAndView entity for further match creation.
     * @param modelAndView gets add-match.html page
     * @return modelAndView
     */
    @GetMapping("/add-match")
    public ModelAndView getAddMatchPage(ModelAndView modelAndView) {
        modelAndView.addObject("match", new Match());
        modelAndView.addObject("teams", teamService.findAllTeams_Sorted());
        modelAndView.setViewName("admin/add-match");

        return modelAndView;
    }

    /**
     * Adds new match to the database if Match entity is valid.
     * @param modelAndView returns to the admin.html page
     * @param match validated entity of Match class
     * @param bindingResult entity for checking if page has errors while completing the task
     * @return modelAndView
     */
    @PostMapping("/add-match")
    public ModelAndView addMatch(ModelAndView modelAndView,
                                 @Valid @ModelAttribute("match") Match match,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/add-match");
        } else {
            addMatchApi(match);
            modelAndView.addObject("successMessage", "Match has been created successfully");
            modelAndView.addObject("match", new Match());
            modelAndView.setViewName("admin/admin");

        }

        return modelAndView;
    }

    /**
     * Gets matches and endedMatches objects from database for showing it on an end-match.html page.
     * @param modelAndView gets end-match.html page
     * @return modelAndView
     */
    @GetMapping("/end-match")
    public ModelAndView getEndMatchPage(ModelAndView modelAndView) {
        modelAndView.addObject("matches", matchService.getAllMatchesNotEnded_Admin());
        modelAndView.addObject("endedMatches", matchService.getAllEndedMatches_Admin());
        modelAndView.setViewName("admin/end-match");

        return modelAndView;
    }

    /**
     * Finds match by id in database and adds it to the modelAndView.
     * @param modelAndView returns to the endMatchById.html page
     * @param id unique integer value which belongs to particular match
     * @return modelAndView
     */
    @GetMapping("/end-match/{id}")
    public ModelAndView getEndMatch(ModelAndView modelAndView,
                                 @PathVariable Integer id) {
        Match match = matchService.findMatchById(id);

        modelAndView.addObject("matchToEnd", match);

        modelAndView.setViewName("admin/endMatchById");
        return modelAndView;
    }

    /**
     * Returns the same page, but with error warning
     * @param modelAndView returns to the endMatchById.html page
     * @param id unique integer value which belongs to particular match
     * @return modelAndView
     */
    @GetMapping("/end-match/error/{id}")
    public ModelAndView getEndMatchError(ModelAndView modelAndView,
                                    @PathVariable Integer id) {
        Match match = matchService.findMatchById(id);

        modelAndView.addObject("error", true);
        modelAndView.addObject("errorMessage", "Team scores mustn't be null");

        modelAndView.addObject("matchToEnd", match);

        modelAndView.setViewName("admin/endMatchById");
        return modelAndView;
    }

    /**
     * Ends match with given team scores and saves it to the database if Guide entity is valid.
     * DateTimeFormatters format the date and time and set it to the endMatchById() method
     * for further usage in the service.
     * @param modelAndView returns redirect to the end-match.html page
     * @param id unique integer value which belongs to particular match
     * @param matchToEnd validated entity of Match class
     * @param bindingResult entity for checking if page has errors while completing the task
     * @return modelAndView
     */
    @PostMapping("/end-match/{id}")
    public ModelAndView endMatch(ModelAndView modelAndView,
                                 @PathVariable Integer id,
                                 @Valid @ModelAttribute("matchToEnd") Match matchToEnd,
                                 BindingResult bindingResult) {
        Match match = matchService.findMatchById(matchToEnd.getId());

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("matchToEnd", match);
            modelAndView.setViewName("redirect:/admin/end-match/error/" + id);
        } else {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter dateTimeFormatterOnlyDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String timeEnded = LocalDateTime.now().format(dateTimeFormatter);
            String dateEnded = LocalDateTime.now().format(dateTimeFormatterOnlyDate);

            matchService.endMatchById(id, matchToEnd.getFirstTeamScore(), matchToEnd.getSecondTeamScore(),
                    timeEnded, dateEnded);
            modelAndView.setViewName("redirect:/admin/end-match");
        }

        return modelAndView;
    }

    /**
     * Creates the URI address with given team name.
     * @param team the entity of Team class with given data
     * @return created URI location for teams
     */
    public ResponseEntity<Match> addTeamApi(@RequestBody Team team) {
        teamService.addTeam(team);

        HttpHeaders headers = new HttpHeaders();
        String teamName = team.getName().toLowerCase().replace(" ", "%20");
        headers.setLocation(URI.create("http://localhost:8080/team/" + teamName));

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Creates the URI address with given match id.
     * @param match the entity of Match class with given data
     * @return created URI location for matches
     */
    public ResponseEntity<Match> addMatchApi(@RequestBody Match match) {
        matchService.addMatch(match);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/matches/" + match.getId()));

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
