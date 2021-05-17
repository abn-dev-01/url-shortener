package com.abndev.shortener.mvc;

import com.abndev.shortener.service.UrlService;
import com.abndev.shortener.view.model.HomeModel;
import com.abndev.shortener.view.model.ResultModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final UrlService urlServiceImpl;

    @GetMapping(value = {"/"})
    public String showHomePage(ModelMap model) {
        model.addAttribute("homeModel", new HomeModel());
        return "index";
    }

    @PostMapping(value = {"/"})
    public String addDataFromHomePage(@ModelAttribute HomeModel homeModel, ModelMap model) {
        ResultModel resultModel = urlServiceImpl.createShortUrl(homeModel.getUrl());
        model.addAttribute("resultModel", resultModel);
        return "result";
    }
}
