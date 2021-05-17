package com.abndev.shortener.mvc;

import com.abndev.shortener.view.model.HomeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoController {

    @GetMapping(value = {"/"})
    public String showHomePage(ModelMap model) {
        model.addAttribute("homeModel", new HomeModel());
        return "index";
    }
}
