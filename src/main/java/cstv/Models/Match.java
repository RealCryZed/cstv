package cstv.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time_of_start")
    private String timeOfStart;

    @Column(name = "first_team_name")
    @Size(min = 2, message = "Team name must be at least 2 letter")
    private String firstTeamName;

    @Transient
    private Integer firstTeamScore;

    @Column(name = "second_team_name")
    @Size(min = 2, message = "Team name must be at least 2 letter")
    private String secondTeamName;

    @Transient
    private Integer secondTeamScore;

    @Column(name = "tournament")
    @Size(min = 5, message = "Tournament name must be at least 5 letter")
    private String tournament;

    @Column(name = "ended")
    private Integer ended = 0;
}
