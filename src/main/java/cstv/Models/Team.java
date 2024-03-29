package cstv.Models;

import cstv.Services.TeamService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Document(collection = "teams")
@NoArgsConstructor
public class Team {

    @Transient
    public static final String SEQUENCE_NAME = "teams_sequence";

    @Id
    private Integer id;

    @Size(min = 2, message = "Team name must be at least 2 letter")
    private String name;

    @Indexed(name = "place")
    @NotNull(message = "Place mustn't be null")
    private Integer place;

    @Transient
    private String player1;
    @Transient
    private String player2;
    @Transient
    private String player3;
    @Transient
    private String player4;
    @Transient
    private String player5;

    private Set<String> players;
}
