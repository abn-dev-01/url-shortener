package com.abndev.shortener.service;

import com.abndev.shortener.jpa.UrlsRepository;
import com.abndev.shortener.model.UrlsEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UrlServiceImplTest {

    @Autowired
    private UrlServiceImpl urlService;

    @Value("${new.url.domain}")
    private String newDomain;

    @Value("${new.url.suffix}")
    private String newSuffix;

    @MockBean
    private UrlsRepository urlsRepository;

    @Test
    void createShortUrl() {
        final String d1 = "http://bingo.com";
        final String p1 = "/win/1";
        String url1 = d1 + p1;

        final String HEXID = "a";
        final long GUID = 10L;

        List<UrlsEntity> mockResult = Arrays.asList(
            UrlsEntity.builder().domain(d1).path(p1).id(GUID).hexid(HEXID).build()
        );
        when(urlsRepository.findByDomainAndPath(d1, p1))
            .thenReturn(mockResult);

        var result = urlService.createShortUrl(url1);
        var expected = newDomain + newSuffix + "/" + HEXID;

        Assertions.assertFalse(result.isError());
        Assertions.assertNull(result.getErrorMessage());
        Assertions.assertEquals(result.getNewUrl(), expected);
    }

    @Test
    void getNewUrl() {
        final String path = "new-path-1";
        var result = urlService.getNewUrl(path);
        var expected = newDomain + newSuffix + "/" + path;
        Assertions.assertEquals(result, expected);
    }
}
