package com.abndev.shortener.service;

import com.abndev.shortener.exceptions.IncorrectUrlException;
import com.abndev.shortener.model.UrlDto;
import com.abndev.shortener.view.model.ResultModel;
import com.abndev.shortener.model.UrlsEntity;

import java.util.List;

public interface UrlService {

    ResultModel createShortUrl(String url);

    String getNewUrl(String path);

    UrlsEntity saveUrl(UrlDto url)
        throws IncorrectUrlException;

    List<UrlsEntity> findUrl(UrlDto url);
}
