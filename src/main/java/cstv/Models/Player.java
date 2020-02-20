package cstv.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "players")
public class Player {

    @Id
    private Integer id;

    @Column(name = "nickname")
    @Size(min = 2, message = "Nickname must be at least 2 letter")
    private String nickname;

    @Column(name = "place")
    @NotNull(message = "Place mustn't be null")
    private Integer place;

    @Column(name = "kd")
    @NotNull(message = "KD mustn't be null")
    private Float kd;
}
