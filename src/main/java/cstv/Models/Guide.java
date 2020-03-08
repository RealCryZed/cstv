package cstv.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Data
@Document(collection = "guides")
public class Guide {

    @Transient
    public static final String SEQUENCE_NAME = "guides_sequence";

    @Id
    private Integer id;

    @Indexed(name = "timeOfCreation", direction = IndexDirection.DESCENDING)
    private String timeOfCreation;

    @Indexed(name = "dateOfCreation", direction = IndexDirection.DESCENDING)
    private String dateOfCreation;

    private String author;

    @Size(min = 3, message = "Headline must be at least 3 letter")
    private String headline;

    @Size(min = 2, message = "Theme must be at least 2 letter")
    private String theme;

    @Indexed(name = "text")
    @Size(min = 10, message = "Text must be at least 10 letter")
    private String text;
}
