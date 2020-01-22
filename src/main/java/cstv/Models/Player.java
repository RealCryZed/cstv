package cstv.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "players")
public class Player {

    @Id
    private Long id;

    private String nickname;
    private Integer place;
}
