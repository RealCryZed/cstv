package cstv.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "matches")
public class Match{

    @Transient
    public static final String SEQUENCE_NAME = "matches_sequence";

    @Id
    private Long id;

    private String timeOfStart;

    private String firstTeamName;
    private Integer firstTeamScore;

    private String secondTeamName;
    private Integer secondTeamScore;

    private String tournament;

    private Integer ended = 0;
}