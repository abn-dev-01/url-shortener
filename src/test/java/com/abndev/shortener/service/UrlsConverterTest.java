package com.abndev.shortener.service;

import com.abndev.shortener.exceptions.IncorrectUrlException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlsConverterTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Domain in the URL is Case Insensitive. ")
    void optimizeUrl()
        throws IncorrectUrlException {

        String url1 = "HTTP://www.GOOGLE.com/iphone/and/other";
        String result = UrlsConverter.optimizeUrl(url1);
        Assertions.assertEquals(url1.toLowerCase(), result);

        url1 = "HTTPs://www.apple.com/iphone/123";
        result = UrlsConverter.optimizeUrl(url1);
        Assertions.assertEquals(url1.toLowerCase(), result);
    }

    @Test
    @DisplayName("DOMAIN ONLY: Domain in the URL is Case Insensitive. ")
    void optimizeUrlDOmainOnly()
        throws IncorrectUrlException {

        String url1 = "HTTP://www.GOOGLE.com";
        String result = UrlsConverter.optimizeUrl(url1);
        Assertions.assertEquals(url1.toLowerCase(), result);
    }

    @Test
    @DisplayName("Domain in the URL is Case Insensitive. ")
    void optimizeUrlThrow()
        throws IncorrectUrlException {

        final String url1 = "://www.GOOGLE.com/iphone/and/other";
        Assertions.assertThrows(IncorrectUrlException.class,
                                () -> {UrlsConverter.optimizeUrl(url1);}
        );

        final String url2 = "HTTPs://www.apple.com/iphone/123";
    }
}
