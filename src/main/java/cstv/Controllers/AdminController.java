package cstv.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "*")
public class AdminController {


    /**
     * Sets admin modelAndView to open admin.html page.
     * @param modelAndView sets admin.html page
     * @return modelAndView
     */
    @GetMapping("/admin")
    public ModelAndView getAdminPage(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/admin");

        return modelAndView;
    }
}
