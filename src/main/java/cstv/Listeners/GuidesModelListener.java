package cstv.Listeners;

import cstv.Models.Guide;
import cstv.Services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class GuidesModelListener extends AbstractMongoEventListener<Guide> {

    @Autowired
    private SequenceGeneratorService seqGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Guide> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(seqGenerator.generateSequence(Guide.SEQUENCE_NAME));
        }
    }
}
