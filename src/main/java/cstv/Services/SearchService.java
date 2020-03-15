package cstv.Services;

import cstv.Interfaces.*;
import cstv.Models.Guide;
import cstv.Models.Player;
import cstv.Models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private GuidesRepository guidesRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    /**
     * Gets the text user typed and tries to find the relations in database.
     * It tries to find players, teams and guides with given text, and if methods don't return null,
     * adds them to the list with objects.
     * @param searchArray the text user typed in search input block
     * @return list of objects
     */
    public List<Object> findElement(String searchArray) {
        List<Object> obj = new ArrayList<>();

        List<Player> players = playerRepository.findAllByNicknameContainsIgnoreCase(searchArray);
        if (players != null) {
            obj.addAll(players);
        }

        List<Team> teams = teamRepository.findAllByNameContainsIgnoreCase(searchArray);
        if (teams != null) {
            obj.addAll(teams);
        }

        List<Guide> guides = guidesRepository.findAllByHeadlineContainsIgnoreCaseOrderByDateOfCreationDescTimeOfCreationDesc(searchArray);
        if (guides != null) {
            obj.addAll(guides);
        }

        return obj;
    }
}
