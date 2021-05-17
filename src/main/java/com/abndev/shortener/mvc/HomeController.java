package com.abndev.shortener.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping(value = {"/"})
    public String showHomePage(ModelMap model) {
//        model.put("name", getLoggedinUserName());
        return "index";
    }

    @PostMapping(value = {"/"})
    public String addDataFromHomePage(ModelMap model) {
//        model.put("name", getLoggedinUserName());
        return "index";
    }
}
