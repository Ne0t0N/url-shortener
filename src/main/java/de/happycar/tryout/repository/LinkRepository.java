package de.happycar.tryout.repository;

import de.happycar.tryout.bean.Link;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Synchronized blocks are just a precaution here as in real application it would probably be a database
 * instead of Map-based cache.
 * <p>
 * User: Ne0t0N
 * Date: 28.05.2016
 */
@Repository
public class LinkRepository {

    private final Map<String, Link> linksCache = new HashMap<>();

    public boolean checkAndSave(String id, Link link) {
        synchronized (linksCache) {
            if (linksCache.containsKey(id)) {
                return false;
            }
            linksCache.put(id, link);
            return true;
        }
    }

    public Link getById(String id) {
        synchronized (linksCache) {
            return linksCache.get(id);
        }
    }

    public Link getByInitial(String initialLink) {
        synchronized (linksCache) {
            for (Map.Entry<String, Link> entry : linksCache.entrySet()) {
                Link link = entry.getValue();
                if (link.getInitialLink().equals(initialLink)) {
                    return link;
                }
            }
            return null;
        }
    }

}
