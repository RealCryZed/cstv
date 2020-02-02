package cstv.Services;

import cstv.Interfaces.GuidesRepository;
import cstv.Models.Guide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Guide addGuide(Guide guide, String author, String timePublished, String datePublished) {
        guide.setId(seqGenerator.generateSequence(Guide.SEQUENCE_NAME));
        guide.setAuthor(author);
        guide.setTimeOfCreation(timePublished);
        guide.setDateOfCreation(datePublished);

        return guideRepo.save(guide);
    }

    public Page<Guide> getLast15Guides() {
        PageRequest page = PageRequest.of(
                0, 15, Sort.by("_id").ascending());
        return guideRepo.findAll(page);
    }
}
