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

    /**
     * Finds guide with specific id in database.
     * @param id unique value that related to guide
     * @return guide that has been found in database
     */
    public Guide findGuideById(Integer id) {
        return guideRepo.findGuideById(id);
    }

    /**
     * Method for the add-guide.html page.
     * Saves user with given credentials in database.
     * @param guide element of Guide class, which takes all credentials
     * @param author name related to the author of guide
     * @param timePublished the exact time when guide was published
     * @param datePublished the exact date when guide was published
     */
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

    /**
     * Gets the last 15 published guides in database
     * @return list of guides
     */
    public List<GuidesInfo> getLast15GuidesInformation() {
        return guideInfoRepo.findTop15ByOrderByDateOfCreationDescTimeOfCreationDesc();
    }
}
