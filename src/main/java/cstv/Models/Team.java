package cstv.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Document(collection = "teams")
public class Team {

    @Transient
    public static final String SEQUENCE_NAME = "teams_sequence";

    @Id
    private Long id;

    @Size(min = 2, message = "Team name must be at least 2 letter")
    private String name;

    @NotNull(message = "Place mustn't be null")
    private Integer place;
}
