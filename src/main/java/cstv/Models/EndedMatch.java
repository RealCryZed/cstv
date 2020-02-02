package cstv.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Document(collection = "ended_matches")
public class EndedMatch {

//    @Transient
//    public static final String SEQUENCE_NAME = "ended_matches_sequence";

    @Id
    private Long id;

    private String endedTime;
    private String endedDate;

    private String firstTeamName;
    @NotNull(message = "Team score mustn't be null")
    private Integer firstTeamScore;
    private String firstTeamState;

    private String secondTeamName;
    @NotNull(message = "Team score mustn't be null")
    private Integer secondTeamScore;
    private String secondTeamState;

    private String tournament;

    private Integer ended = 1;
}