package cstv.Services;

import cstv.Interfaces.GuidesRepository;
import cstv.Models.Guide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class GuideService {

    @Autowired
    private GuidesRepository guideRepo;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    public Guide addGuide(Guide guide, String author, String timePublished) {
        guide.setId(seqGenerator.generateSequence(Guide.SEQUENCE_NAME));
        guide.setTimeOfCreation(timePublished);
        guide.setAuthor(author);
        return guideRepo.save(guide);
    }
}
