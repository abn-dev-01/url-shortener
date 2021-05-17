package com.abndev.shortener.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@NoArgsConstructor
@AllArgsConstructor
public class UrlsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * HEX ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

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
