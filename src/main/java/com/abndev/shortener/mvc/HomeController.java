package com.abndev.shortener.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping(value = "/")
    public String showWelcomePage(ModelMap model) {
//        model.put("name", getLoggedinUserName());
        return "index";
    }
}
