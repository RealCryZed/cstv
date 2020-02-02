package cstv.Services;

import cstv.Interfaces.GuidesRepository;
import cstv.Models.Guide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuideService {

    @Autowired
    private GuidesRepository guideRepo;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    public Guide addGuide(Guide guide) {
        guide.setId(seqGenerator.generateSequence(Guide.SEQUENCE_NAME));
        return guideRepo.save(guide);
    }
}
