package cstv.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Document(collection = "players")
public class Player {

    @Id
    private Integer id;

    @Size(min = 2, message = "Nickname must be at least 2 letter")
    private String nickname;

    @NotNull(message = "Place mustn't be null")
    private Integer place;

    @NotNull(message = "KD mustn't be null")
    private Float kd;
}
