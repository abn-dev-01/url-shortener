package com.abndev.shortener.service;

import com.abndev.shortener.exceptions.IncorrectUrlException;
import com.abndev.shortener.view.model.ResultModel;
import com.abndev.shortener.model.UrlsEntity;

import java.util.Optional;

public interface UrlService {

    ResultModel createShortUrl(String url);

    String getNewUrl(String path);

    UrlsEntity saveUrl(String url)
        throws IncorrectUrlException;

    Optional<UrlsEntity> findUrl(String url);
}
