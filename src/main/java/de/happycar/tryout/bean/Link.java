package de.happycar.tryout.bean;

import java.io.Serializable;

/**
 * User: Ne0t0N
 * Date: 28.05.2016
 */
public class Link implements Serializable {

    private String initialLink;
    private String shortenedLink;

    public Link() {
    }

    public Link(String initialLink, String shortenedLink) {
        this.initialLink = initialLink;
        this.shortenedLink = shortenedLink;
    }

    public String getInitialLink() {
        return initialLink;
    }

    public void setInitialLink(String initialLink) {
        this.initialLink = initialLink;
    }

    public String getShortenedLink() {
        return shortenedLink;
    }

    public void setShortenedLink(String shortenedLink) {
        this.shortenedLink = shortenedLink;
    }
}
