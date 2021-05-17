package com.abndev.shortener.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlDto {
    private String domain;
    private String path;

    public String getOptimizedUrl() {
        return domain + (domain.endsWith("/") ? "" : "/") + path;
    }
}
