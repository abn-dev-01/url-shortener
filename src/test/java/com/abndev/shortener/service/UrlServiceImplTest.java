package com.abndev.shortener.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//    ,
//    properties = {"new.url.domain=https://localhost:9999"}
    )
//@ActiveProfiles("test")
class UrlServiceImplTest {

    @Autowired
    private UrlServiceImpl urlService;

    @Value("${new.url.domain}")
    private String newDomain;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createShortUrl() {
    }

    @Test
    void getNewUrl() {
        final String path = "new-path-1";
        var result = urlService.getNewUrl(path);
        var expected = newDomain + "/" + path;
        Assertions.assertEquals(result, expected);
    }

    @Test
    void saveUrl() {
    }

    @Test
    void findUrl() {
    }
}
