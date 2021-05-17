package com.abndev.shortener.jpa;

import com.abndev.shortener.model.UrlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UrlsRepository extends JpaRepository<UrlsEntity, String>, JpaSpecificationExecutor<UrlsEntity> {

}
