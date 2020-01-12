package cstv.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RankingController {

    @RequestMapping("/ranking")
    public ModelAndView getRankingPage(ModelAndView modelAndView) {
        modelAndView.setViewName("ranking");

        return modelAndView;
    }
}
