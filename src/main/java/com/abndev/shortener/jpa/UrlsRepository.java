package com.abndev.shortener.jpa;

import com.abndev.shortener.model.UrlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UrlsRepository extends JpaRepository<UrlsEntity, String>, JpaSpecificationExecutor<UrlsEntity> {

    List<UrlsEntity> findByDomainAndPath(String domain, String path);
}
