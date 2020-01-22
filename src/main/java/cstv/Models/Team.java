package cstv.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "teams")
public class Team {

    @Id
    private Long id;

    private String name;
    private Integer place;
}
