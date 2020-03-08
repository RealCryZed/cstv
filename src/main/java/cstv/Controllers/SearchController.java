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

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public ModelAndView getSearchPage(ModelAndView modelAndView,
                                      @RequestParam(name = "navbar-search-input") String searchText) {
        modelAndView.setViewName("search");

//        String[] splitedText = searchText.split("\\s+");

        List<Object> listOfObjects = searchService.findElement(searchText);

        for (Object object : listOfObjects) {
            if (object instanceof Guide) {
                modelAndView.addObject("guideInstance", object);
            } else if (object instanceof Player) {
                modelAndView.addObject("playerInstance", object);
            } else if (object instanceof Team) {
                modelAndView.addObject("teamInstance", object);
            }
        }

        return modelAndView;
    }
}
