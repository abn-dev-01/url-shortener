package com.abndev.shortener.service;

import com.abndev.shortener.exceptions.IncorrectUrlException;
import com.abndev.shortener.jpa.UrlsRepository;
import com.abndev.shortener.model.UrlDto;
import com.abndev.shortener.model.UrlsEntity;
import com.abndev.shortener.view.model.ResultModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlServiceImpl implements UrlService {

    private final UrlsRepository urlsRepository;
    private UrlsConverter urlsConverter;

    @Value("${new.url.domain}")
    private String newUrlDomain;

    @Value("${new.url.suffix}")
    private String newUrlSuffix;

    @Override
    public ResultModel createShortUrl(final String url) {

        try {
            var urlDto = UrlsConverter.optimizeUrl(url);

            // we have to check is so url in the DB or not, find it in the DB.
            var urls = findUrl(urlDto);

            UrlsEntity urlEntity;
            if (urls == null || urls.size() == 0) {
                urlEntity = saveUrl(urlDto);
            } else {
                LOG.debug(" Checking URL: {}. Found records in DB: {}", url, urls.size());
                urlEntity = urls.get(0);
            }
            return ResultModel.builder()
                              .oldUrl(url)
                              .newUrl(getNewUrl(urlEntity.getHexid()))
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
        return newUrlDomain + newUrlSuffix + "/" + path;
    }

    @Override
    @Transactional
    public UrlsEntity saveUrl(UrlDto urlDto)
        throws IncorrectUrlException {

        LOG.debug(" START saving url: {}", urlDto);
        var entity = UrlsEntity.builder()
                               .domain(urlDto.getDomain())
                               .path(urlDto.getPath())
                               .build();

        var savedEnity = urlsRepository.save(entity);

        // update HXID 
        savedEnity.setHexid(Long.toHexString(savedEnity.getId()));
        savedEnity = urlsRepository.save(savedEnity);

        LOG.debug(" END saving url: {}", urlDto);

        return savedEnity;
    }

    @Override
    public List<UrlsEntity> findUrl(UrlDto urlDto) {
        List<UrlsEntity> result = urlsRepository.findByDomainAndPath(urlDto.getDomain(), urlDto.getPath());
        return result;
    }

    @Override
    public String getRedirectUrl(String hexid) {
        List<UrlsEntity> urls = urlsRepository.findByHexid(hexid);
        if (urls != null && urls.size() == 1) {
            final UrlsEntity entity = urls.get(0);
            return entity.getDomain() + entity.getPath();
        }
        return newUrlDomain;
    }
}
