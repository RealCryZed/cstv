package cstv.Interfaces;

import cstv.Models.Guide;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuidesRepositoryTest {

    @Autowired
    private GuidesRepository guidesRepository;

    @Test
    public void testGuidesRepository_whenSaveAndRetrieveEntity_thenOK() {
        Guide guide = new Guide();
        guide.setId(999999);
        guide.setHeadline("WOW WOW WOW WHAT A GAME");
        guide.setAuthor("RealCryZed");
        guide.setText("That was a great game!");
        guide.setTheme("Games");

        guidesRepository.save(guide);
        Guide foundGuide = guidesRepository.findGuideById(guide.getId());

        assertNotNull(foundGuide);
        assertEquals(guide.getAuthor(), foundGuide.getAuthor());
        assertEquals(guide.getHeadline(), foundGuide.getHeadline());

        guidesRepository.deleteById(guide.getId());
    }

    @Test
    public void testGuidesRepository_whenFindAll_thenOK() {
        List<Guide> guides = guidesRepository.findAll();
        assertThat(guides.size(), is(greaterThanOrEqualTo(0)));
    }
}