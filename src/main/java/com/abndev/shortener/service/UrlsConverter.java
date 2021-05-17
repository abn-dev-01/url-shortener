package com.abndev.shortener.service;

import com.abndev.shortener.exceptions.IncorrectUrlException;
import com.abndev.shortener.model.UrlDto;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class UrlsConverter {

    private static final String DOMAIN_REGEX = "^([^:\\/?#]+:\\/\\/[^\\/?#]*)?(.*)?";

    private UrlsConverter() { }

    /**
     * Converts URL: the protocol and domain is Case Insensitive, so we convert it to a lower case.
     *
     * @param urlFromForm ~ url from the Form
     *
     * @return optimized URL, where Protocol + domain are in Lower case.
     *
     * @throws IncorrectUrlException - when given URL is incorrect.
     */
    public static UrlDto optimizeUrl(final String urlFromForm)
        throws IncorrectUrlException {

        var pattern = Pattern.compile(DOMAIN_REGEX);
        var matcher = pattern.matcher(urlFromForm);

        if (matcher.find()) {
            if (matcher.groupCount() > 1) {
                final String group1 = matcher.group(1);
                if (group1 == null) {
                    throw new IncorrectUrlException();
                }
                var domain = group1.toLowerCase();
                var path = "";
                if (matcher.groupCount() == 2 && matcher.group(2) != null) {
                    path = matcher.group(2);
                }
                var urlDto = UrlDto.builder().domain(domain).path(path).build();
                LOG.debug("Old url: {} \nnew url: {}", urlFromForm, urlDto);

                return urlDto;
            } else {
                throw new IncorrectUrlException();
            }
        } else {
            throw new IncorrectUrlException();
        }
    }
}
