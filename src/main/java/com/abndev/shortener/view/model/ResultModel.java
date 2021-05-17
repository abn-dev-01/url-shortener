package com.abndev.shortener.view.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResultModel {
    private boolean isError;
    private String errorMessage;
    private String newUrl;
    private String oldUrl;
}
