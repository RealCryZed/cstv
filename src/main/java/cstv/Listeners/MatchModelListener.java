package cstv.Listeners;

import cstv.Models.Match;
import cstv.Services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class MatchModelListener extends AbstractMongoEventListener<Match> {

    @Autowired
    private SequenceGeneratorService seqGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Match> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(seqGenerator.generateSequence(Match.SEQUENCE_NAME));
        }
    }
}
