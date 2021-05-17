package com.abndev.shortener.service;

import com.abndev.shortener.exceptions.IncorrectUrlException;
import com.abndev.shortener.jpa.UrlsRepository;
import com.abndev.shortener.view.model.ResultModel;
import com.abndev.shortener.model.UrlsEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlServiceImpl implements UrlService {

    private final UrlsRepository urlsRepository;
    private UrlsConverter urlsConverter;

    @Value("${new.url.domain}")
    private String newUrlDomain;

    @Override
    public ResultModel createShortUrl(final String url) {

        // we have to check is so url in the DB or not, find it in the DB.
        Optional<UrlsEntity> urlOptional = findUrl(url);

        UrlsEntity urlEntity;
        try {
            if (!urlOptional.isPresent()) {
                urlEntity = saveUrl(url);
            } else {
                urlEntity = urlOptional.get();
            }
            return ResultModel.builder()
                              .oldUrl(url)
                              .newUrl(getNewUrl(urlEntity.getId()))
                              .build();
        } catch (IncorrectUrlException e) {
            return ResultModel.builder()
                              .isError(true)
                              .errorMessage("Incorrect URL.")
                              .build();
        }
    }

    @Override
    public String getNewUrl(String path) {
        return newUrlDomain + (newUrlDomain.endsWith("/") ? "" : "/") + path;
    }

    @Override
    public UrlsEntity saveUrl(String url)
        throws IncorrectUrlException {

        LOG.debug(" START saving url: {}", url);
        String optimizedUrl = UrlsConverter.optimizeUrl(url);
        UrlsEntity entity = UrlsEntity.builder()
                                      .build();

        final UrlsEntity save = urlsRepository.save(entity);
        LOG.debug(" END saving url: {}", url);
        
        return save;
    }

    @Override
    public Optional<UrlsEntity> findUrl(String url) {
        return null;
    }
}
