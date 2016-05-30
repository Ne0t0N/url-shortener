package de.happycar.tryout.service;

import de.happycar.tryout.bean.Link;
import de.happycar.tryout.repository.LinkRepository;
import de.happycar.tryout.util.IdUtils;
import de.happycar.tryout.util.LinkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Ne0t0N
 * Date: 28.05.2016
 */
@Service
public class LinkService {

    private LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link minifyAndSave(String initialLink) {
        Link link;
        boolean success;
        do {
            String id = IdUtils.generateId();
            String shortLink = LinkUtils.createURL(LinkUtils.SHORT_PATH) + id;
            link = new Link(initialLink, shortLink);
            success = linkRepository.checkAndSave(id, link);
        } while (!success);
        return link;
    }

    public Link getById(String id) {
        return linkRepository.getById(id);
    }

    public Link getByInitial(String initialLink) {
        return linkRepository.getByInitial(initialLink);
    }

}
