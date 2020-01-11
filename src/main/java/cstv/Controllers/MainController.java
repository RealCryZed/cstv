package cstv.Controllers;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "*")
public class MainController {

    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping("/")
    public ModelAndView home() {
        modelAndView.setViewName("home");

        return modelAndView;
    }
}
