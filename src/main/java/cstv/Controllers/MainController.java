package cstv.Controllers;

import cstv.Models.GuidesInfo;
import cstv.Services.GuideService;
import cstv.Services.MatchService;
import cstv.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private GuideService guideService;

    /**
     * Returns home.html page.
     * The List<GuidesInfo> takes last 15 guides from database.
     * For loop sorts guides into blocks by added date and then sets the formatted date
     * in the ELEMENT_NAME_onView item.
     * If date of looping guide doesn't equal to given date, it saves the List<GuidesInfo>
     * into List<List<GuidesInfo>> and clear its value by creating a new List element.
     * After all calculations, method adds objects to show them on a html page
     * Adds objects of elements needed in displaying
     * @param modelAndView gets home.html page
     * @return modelAndView
     */

    @RequestMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("home");

        List<GuidesInfo> guidesListFromDB = guideService.getLast15GuidesInformation();
        List<GuidesInfo> newGuidesInfoList = new LinkedList<>();

        List<List<GuidesInfo>> listOfGuidesInfoList = new LinkedList<>();
        String startElementDateOfCreation = guidesListFromDB.get(0).getDateOfCreation();

        for (int i = 0; i < guidesListFromDB.size(); i++) {
            if (guidesListFromDB.get(i).getDateOfCreation().equals(startElementDateOfCreation) && i != guidesListFromDB.size() - 1) {
                newGuidesInfoList.add(guidesListFromDB.get(i));
            } else {
                try {
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    SimpleDateFormat dateFormatter2 = new SimpleDateFormat("MMM, dd", Locale.US);

                    String oldDate = newGuidesInfoList.get(0).getDateOfCreation();
                    Date date = dateFormatter.parse(oldDate);

                    newGuidesInfoList.get(0).setDateOfCreation_onView(dateFormatter2.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                listOfGuidesInfoList.add(newGuidesInfoList);

                List<GuidesInfo> newLinkedListAfterFalse = new LinkedList<>();

                newGuidesInfoList = newLinkedListAfterFalse;
                newGuidesInfoList.add(guidesListFromDB.get(i));
                startElementDateOfCreation = guidesListFromDB.get(i).getDateOfCreation();
            }
        }

        modelAndView.addObject("teams", teamService.getTenFirstTeams());
        modelAndView.addObject("matches", matchService.getFiveLastMatchesNotEnded());
        modelAndView.addObject("endedMatches", matchService.getFiveLastEndedMatches());

        modelAndView.addObject("guidesByDateLists", listOfGuidesInfoList);

        return modelAndView;
    }
}
