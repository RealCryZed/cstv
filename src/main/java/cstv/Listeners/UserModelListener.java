package cstv.Listeners;

import cstv.Models.User;
import cstv.Services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class UserModelListener extends AbstractMongoEventListener<User> {

    @Autowired
    private SequenceGeneratorService seqGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(seqGenerator.generateSequence(User.SEQUENCE_NAME));
        }
    }
}
