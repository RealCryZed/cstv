package cstv.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "ended_matches")
public class EndedMatch {

    @Id
    @Column(name = "id_ended")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnded;

    @Column(name = "id")
    private Integer id;

    @Column(name = "ended_time")
    private String endedTime;

    @Column(name = "ended_date")
    private String endedDate;

    @Column(name = "first_team_name")
    private String firstTeamName;

    @Column(name = "first_team_score")
    @NotNull(message = "Team score mustn't be null")
    private Integer firstTeamScore;

    @Column(name = "first_team_state")
    private String firstTeamState;

    @Column(name = "second_team_name")
    private String secondTeamName;

    @Column(name = "second_team_score")
    @NotNull(message = "Team score mustn't be null")
    private Integer secondTeamScore;

    @Column(name = "second_team_state")
    private String secondTeamState;

    private String tournament;

    private Integer ended = 1;
}