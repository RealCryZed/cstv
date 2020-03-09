package cstv.Services;

import cstv.Interfaces.*;
import cstv.Models.Guide;
import cstv.Models.Player;
import cstv.Models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private GuidesRepository guidesRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<Object> findElement(String searchArray) {
        List<Object> obj = new ArrayList<>();
//        List<String> searchAsk = new LinkedList<>();
//
//        for (String search : searchArray) {
//            System.err.println(search);
//            searchList.add(search);
//        }

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
