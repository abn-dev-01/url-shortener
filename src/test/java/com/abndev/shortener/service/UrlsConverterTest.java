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

        var url1 = "HTTP://www.GOOGLE.com/iphone/and/other";
        var result = UrlsConverter.optimizeUrl(url1);
        Assertions.assertEquals(url1.toLowerCase(), result.getOptimizedUrl());

        url1 = "HTTPs://www.apple.com/iphone/123";
        result = UrlsConverter.optimizeUrl(url1);
        Assertions.assertEquals(url1.toLowerCase(), result.getOptimizedUrl());
    }

    @Test
    @DisplayName("DOMAIN ONLY: Domain in the URL is Case Insensitive. ")
    void optimizeUrlDOmainOnly()
        throws IncorrectUrlException {

        var url1 = "HTTP://www.GOOGLE.com";
        var result = UrlsConverter.optimizeUrl(url1);
        Assertions.assertEquals(url1.toLowerCase(), result.getOptimizedUrl());
        
        url1 = "HTTP://www.GOOGLE.com/";
        result = UrlsConverter.optimizeUrl(url1);
        Assertions.assertEquals(url1.toLowerCase(), result.getOptimizedUrl());
    }

    @Test
    @DisplayName("Domain in the URL is Case Insensitive. ")
    void optimizeUrlThrow()
        throws IncorrectUrlException {

        final String url1 = "://www.GOOGLE.com/iphone/and/other";
        Assertions.assertThrows(IncorrectUrlException.class,
                                () -> {UrlsConverter.optimizeUrl(url1);}
        );
    }
}
