package cstv.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Document(collection = "matches")
public class Match{

    @Transient
    public static final String SEQUENCE_NAME = "matches_sequence";

    @Id
    private Long id;

    private String timeOfStart;

    @Size(min = 2, message = "Team name must be at least 2 letter")
    private String firstTeamName;
    @NotNull(message = "Team score mustn't be null")
    private Integer firstTeamScore;

    @Size(min = 2, message = "Team name must be at least 2 letter")
    private String secondTeamName;
    @NotNull(message = "Team score mustn't be null")
    private Integer secondTeamScore;

    @Size(min = 5, message = "Tournament name must be at least 5 letter")
    private String tournament;

    private Integer ended = 0;
}
