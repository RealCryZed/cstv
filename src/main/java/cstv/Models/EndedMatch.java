package cstv.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "ended_matches")
public class EndedMatch {

//    @Transient
//    public static final String SEQUENCE_NAME = "ended_matches_sequence";

    @Id
    private Long id;

    private Date endedTime;

    private String firstTeamName;
    private Integer firstTeamScore;

    private String secondTeamName;
    private Integer secondTeamScore;

    private String tournament;

    private Integer ended = 1;
}