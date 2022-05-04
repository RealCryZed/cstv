package cstv.Controllers;

import cstv.Models.Guide;
import cstv.Models.Player;
import cstv.Models.Team;
import cstv.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * Gets the text user typed in a search input block.
     * Finds entities of Guide, Team and Player classes by given text.
     * For loop is sorting every Object by its instance to the specific lists and then adds them to the object
     * @param modelAndView gets search.html page
     * @param searchText the text user types in a search input block
     * @return modelAndView
     */
    @GetMapping("/search")
    public ModelAndView getSearchPage(ModelAndView modelAndView,
                                      @RequestParam(name = "navbar-search-input") String searchText) {
        modelAndView.setViewName("search");

        List<Object> listOfObjects = searchService.findElement(searchText);

        List<Object> listOfGuides = new LinkedList<>();
        List<Object> listOfTeams = new LinkedList<>();
        List<Object> listOfPlayers = new LinkedList<>();

        for (Object object : listOfObjects) {
            if (object instanceof Guide) {
                listOfGuides.add(object);
            } else if (object instanceof Team) {
                listOfTeams.add(object);
            } else if (object instanceof Player) {
                listOfPlayers.add(object);
            }
        }

        modelAndView.addObject("guideInstance", listOfGuides);
        modelAndView.addObject("playerInstance", listOfPlayers);
        modelAndView.addObject("teamInstance", listOfTeams);

        return modelAndView;
    }
}
