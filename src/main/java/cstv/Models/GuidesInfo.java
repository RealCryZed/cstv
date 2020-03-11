package cstv.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("guides_info")
public class GuidesInfo {

    @Id
    private Integer id;

    @Indexed
    private String headline;

    @Indexed(direction = IndexDirection.DESCENDING)
    private String timeOfCreation;

    @Indexed(direction = IndexDirection.DESCENDING)
    private String dateOfCreation;

    @Transient
    private String dateOfCreation_onView;
}
