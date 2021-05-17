package com.abndev.shortener.mvc;

import com.abndev.shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GoController {

    private final UrlService urlService;

    @GetMapping(value = {"/${new.url.suffix}/{hexid}"})
    public String showHomePage(@PathVariable String hexid) {
        String redirectUrl = urlService.getRedirectUrl(hexid);
        return "redirect:" + redirectUrl;
    }
}
