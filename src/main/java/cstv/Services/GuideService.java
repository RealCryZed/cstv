package cstv.Services;

import cstv.Interfaces.GuidesRepository;
import cstv.Models.Guide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideService {

    @Autowired
    private GuidesRepository guideRepo;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    public Guide findGuideById(Integer id) {
        return guideRepo.findGuideById(id);
    }

    public Guide addGuide(Guide guide, String author, String timePublished, String datePublished) {
        guide.setId(seqGenerator.generateSequence(Guide.SEQUENCE_NAME));
        guide.setAuthor(author);
        guide.setTimeOfCreation(timePublished);
        guide.setDateOfCreation(datePublished);

        return guideRepo.save(guide);
    }

    public List<Guide> getLast15Guides() {
        return guideRepo.findTop5ByOrderByDateOfCreationDesc();
    }
}
