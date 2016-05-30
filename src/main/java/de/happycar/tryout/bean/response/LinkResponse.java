package de.happycar.tryout.bean.response;

import de.happycar.tryout.bean.Link;
import de.happycar.tryout.bean.type.Message;

import java.io.Serializable;

/**
 * User: Ne0t0N
 * Date: 28.05.2016
 */
public class LinkResponse implements Serializable {

    private Link link;
    private Message message;

    public LinkResponse() {
    }

    public LinkResponse(Message message) {
        this.message = message;
    }

    public LinkResponse(Link link, Message message) {
        this.link = link;
        this.message = message;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String getMessage() {
        return message.getMessage();
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
