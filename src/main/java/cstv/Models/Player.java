package cstv.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "players")
public class Player {

    @Id
    private Integer id;

    private String nickname;
    private Integer place;
    private Float kd;
}
