package cstv.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "ended_matches")
public class EndedMatch {

    @Id
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