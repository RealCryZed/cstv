package cstv.Services;

import cstv.Interfaces.GuidesInfoRepository;
import cstv.Interfaces.GuidesRepository;
import cstv.Models.Guide;
import cstv.Models.GuidesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideService {

    @Autowired
    private GuidesRepository guideRepo;

    @Autowired
    private GuidesInfoRepository guideInfoRepo;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    public Guide findGuideById(Integer id) {
        return guideRepo.findGuideById(id);
    }

    public void addGuide(Guide guide, String author, String timePublished, String datePublished) {
        Integer guideId = seqGenerator.generateSequence(Guide.SEQUENCE_NAME);

        guide.setId(guideId);
        guide.setAuthor(author);
        guide.setTimeOfCreation(timePublished);
        guide.setDateOfCreation(datePublished);

        GuidesInfo guideInfo = new GuidesInfo();
        guideInfo.setId(guideId);
        guideInfo.setHeadline(guide.getHeadline());
        guideInfo.setDateOfCreation(datePublished);
        guideInfo.setTimeOfCreation(timePublished);

        guideRepo.save(guide);
        guideInfoRepo.save(guideInfo);
    }

    public List<GuidesInfo> getLast15GuidesInformation() {
        return guideInfoRepo.findTop15ByOrderByDateOfCreationDescTimeOfCreationDesc();
    }
}
