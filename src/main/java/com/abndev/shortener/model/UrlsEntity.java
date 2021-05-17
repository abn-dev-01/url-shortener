package com.abndev.shortener.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Contains URL and short link to it.
 */
@Entity
@Table(name = "URLS")
@Data
@Builder
public class UrlsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * HEX ID
     */
    @Id
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "HEXID")
    private String hexid;

    /**
     * This part of the UTL is NOT case sensitive, but put value only in lower case.
     */
    @Column(name = "DOMAIN")
    private String domain;

    /**
     * This part of the URL is case sensitive.
     */
    @Column(name = "PATH")
    private String path;
}
