package cstv.Listeners;

import cstv.Models.Team;
import cstv.Services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class TeamsModelListener extends AbstractMongoEventListener<Team> {

    @Autowired
    private SequenceGeneratorService seqGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Team> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(seqGenerator.generateSequence(Team.SEQUENCE_NAME));
        }
    }
}
