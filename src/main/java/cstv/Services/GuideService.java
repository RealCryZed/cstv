package cstv.Services;

import cstv.Interfaces.GuidesRepository;
import cstv.Models.Guide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GuideService {

    @Autowired
    private GuidesRepository guideRepo;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    public Guide findGuideById(Long id) {
        return guideRepo.findGuideById(id);
    }

    public Guide addGuide(Guide guide, String author, String timePublished, String datePublished) {
        guide.setId(seqGenerator.generateSequence(Guide.SEQUENCE_NAME));
        guide.setAuthor(author);
        guide.setTimeOfCreation(timePublished);
        guide.setDateOfCreation(datePublished);

        return guideRepo.save(guide);
    }

    public Page<Guide> getLast15Guides() {
        PageRequest page = PageRequest.of(
                0, 15, Sort.by("dateOfCreation").descending());
        return guideRepo.findAll(page);
    }
}
